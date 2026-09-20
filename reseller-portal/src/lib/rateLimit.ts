import { Ratelimit } from '@upstash/ratelimit';
import { Redis } from '@upstash/redis';
import crypto from 'crypto';
import { adminDb } from './firebaseAdmin';

interface RateLimitInfo {
  count: number;
  resetTime: number;
}

// Fallback in-memory map for development/testing when Upstash is not configured
const fallbackCache = new Map<string, RateLimitInfo>();

const upstashRatelimit: Record<string, Ratelimit> = {};

function getUpstashLimiter(limit: number, windowSeconds: number): Ratelimit | null {
  if (process.env.UPSTASH_REDIS_REST_URL && process.env.UPSTASH_REDIS_REST_TOKEN) {
    const key = `${limit}_${windowSeconds}`;
    if (!upstashRatelimit[key]) {
      upstashRatelimit[key] = new Ratelimit({
        redis: Redis.fromEnv(),
        limiter: Ratelimit.slidingWindow(limit, `${windowSeconds} s`),
        analytics: false,
        prefix: 'rate_limit:vopo',
        timeout: 5000, // 5 seconds timeout to prevent hanging
      });
    }
    return upstashRatelimit[key];
  }
  return null;
}

export async function checkRateLimit(key: string, limit: number, windowMs: number) {
  const windowSeconds = Math.ceil(windowMs / 1000);
  const limiter = getUpstashLimiter(limit, windowSeconds);

  if (limiter) {
    try {
      // Upstash Ratelimit
      const { success, limit: maxLimit, reset, remaining } = await limiter.limit(key);
      return {
        success,
        remaining,
        resetTime: reset,
        headers: {
          'X-RateLimit-Limit': maxLimit.toString(),
          'X-RateLimit-Remaining': remaining.toString(),
          'Retry-After': success ? '0' : Math.ceil((reset - Date.now()) / 1000).toString()
        }
      };
    } catch (error) {
      console.error('Redis rate limiter error:', error);
      throw new Error('503');
    }
  } else {
    // Use Firestore as a distributed production fallback. An in-memory limiter would
    // be ineffective across multiple serverless instances.
    if (process.env.NODE_ENV === 'production') {
      try {
        const now = Date.now();
        const id = crypto.createHash('sha256').update(key).digest('hex');
        const ref = adminDb.collection('rate_limits').doc(id);
        const result = await adminDb.runTransaction(async (transaction: any) => {
          const snapshot = await transaction.get(ref);
          const current = snapshot.exists ? snapshot.data() : null;
          const resetTime = typeof current?.resetTime === 'number' && current.resetTime > now
            ? current.resetTime
            : now + windowMs;
          const count = typeof current?.count === 'number' && current.resetTime > now
            ? current.count + 1
            : 1;
          transaction.set(ref, { count, resetTime, updatedAt: now }, { merge: true });
          return { count, resetTime };
        });
        const success = result.count <= limit;
        return {
          success,
          remaining: Math.max(0, limit - result.count),
          resetTime: result.resetTime,
          headers: {
            'X-RateLimit-Limit': limit.toString(),
            'X-RateLimit-Remaining': Math.max(0, limit - result.count).toString(),
            'Retry-After': success ? '0' : Math.ceil((result.resetTime - now) / 1000).toString()
          }
        };
      } catch (error) {
        console.error('Firestore rate limiter error:', error);
        throw new Error('503');
      }
    }

    // Local In-Memory Fallback
    const now = Date.now();
    const prefixedKey = `rate_limit:vopo:${key}`;
    let info = fallbackCache.get(prefixedKey);

    if (!info || info.resetTime < now) {
      info = { count: 0, resetTime: now + windowMs };
    }

    info.count += 1;
    fallbackCache.set(prefixedKey, info);

    if (Math.random() < 0.05) {
      for (const [k, v] of fallbackCache.entries()) {
        if (v.resetTime < now) {
          fallbackCache.delete(k);
        }
      }
    }

    const remaining = Math.max(0, limit - info.count);
    const success = info.count <= limit;

    return {
      success,
      remaining,
      resetTime: info.resetTime,
      headers: {
        'X-RateLimit-Limit': limit.toString(),
        'X-RateLimit-Remaining': remaining.toString(),
        'Retry-After': success ? '0' : Math.ceil((info.resetTime - now) / 1000).toString()
      }
    };
  }
}

// Exported for testing purposes
export function resetFallbackCache() {
  fallbackCache.clear();
}
