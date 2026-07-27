import { Ratelimit } from '@upstash/ratelimit';
import { Redis } from '@upstash/redis';

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
    // If we are in production and Upstash is not configured, we MUST NOT use fallback.
    // It would silently bypass limits on a distributed system. Return 503.
    if (process.env.NODE_ENV === 'production') {
      console.error('Redis is not configured in production. Blocking requests to prevent unprotected access.');
      throw new Error('503');
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
