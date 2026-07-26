interface RateLimitInfo {
  count: number;
  resetTime: number;
}

const rateLimits = new Map<string, RateLimitInfo>();

export function checkRateLimit(key: string, limit: number, windowMs: number) {
  const now = Date.now();
  let info = rateLimits.get(key);

  if (!info || info.resetTime < now) {
    info = { count: 0, resetTime: now + windowMs };
  }

  info.count += 1;
  rateLimits.set(key, info);

  // Clean up old entries occasionally
  if (Math.random() < 0.05) {
    for (const [k, v] of rateLimits.entries()) {
      if (v.resetTime < now) {
        rateLimits.delete(k);
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
