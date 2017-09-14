//package org.slerpio.oauth.security;
//
//import java.util.concurrent.TimeUnit;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.stereotype.Component;
//
//@Component
//public class RedisService {
//
//	@Autowired
//	private RedisTemplate<Object, Object> template;
//
//	public Object getValue(final String key) {
//		return template.opsForValue().get(key);
//	}
//
//	public void setValue(final String key, final Object value) {
//		template.opsForValue().set(key, value);
//		template.expire(key, 24, TimeUnit.HOURS);
//	}
//
//	public void delete(String key) {
//		template.delete(key);
//	}
//
//}