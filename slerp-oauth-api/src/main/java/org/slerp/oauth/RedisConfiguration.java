package org.slerp.oauth;

import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * The Redis configuration will be connected with redis server in localhost with
 * port 6379, use {@link RedisTemplate} to handle caching
 * 
 * @see RedisTemplate
 * @author kiditz
 */
@Configuration
@EnableCaching
public class RedisConfiguration extends CachingConfigurerSupport {
	@Bean
	public JedisConnectionFactory jedisConnectionFactory() {
		JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
		jedisConnectionFactory.setHostName("127.0.0.1");
		jedisConnectionFactory.setPort(6379);
		return jedisConnectionFactory;
	}

	@Bean
	public RedisTemplate<Object, Object> redisTemplate() {
		RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<Object, Object>();
		redisTemplate.setConnectionFactory(jedisConnectionFactory());
		redisTemplate.setExposeConnection(true);
		redisTemplate.setDefaultSerializer(new GenericJackson2JsonRedisSerializer());
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		redisTemplate.setHashKeySerializer(new GenericJackson2JsonRedisSerializer());
		redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
		return redisTemplate;
	}

	@Bean
	public RedisCacheManager cacheManager() {
		RedisCacheManager redisCacheManager = new RedisCacheManager(redisTemplate());
		redisCacheManager.setTransactionAware(true);
		redisCacheManager.setLoadRemoteCachesOnStartup(false);
		redisCacheManager.setUsePrefix(true);
		return redisCacheManager;
	}
}
