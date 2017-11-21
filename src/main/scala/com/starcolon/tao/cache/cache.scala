package com.starcolon.tao.cache

import com.redis._

/**
 * Redis cache registry
 * @param [ttl] in seconds
 */
class CacheRegistry(ttl: Int = 300) {
  val redis = new RedisClient("localhost", 6379)
  def readKey(key: String): Option[String] = redis.get(key)
  def writeKey(key: String, value: String) = {
    redis.set(key, value)
    redis.expire(key, ttl)
  }
}