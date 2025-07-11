package com.deepanshu.simpleserver.services.redisDB;

//import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.*;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig{
	
	@Bean
	public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory conFactory){
		RedisTemplate<String,Object> template = new RedisTemplate<>();
		template.setConnectionFactory(conFactory);
//		template.setKeySerializer(new StringRedisSerializer());
//		template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
		return template;
	}
}