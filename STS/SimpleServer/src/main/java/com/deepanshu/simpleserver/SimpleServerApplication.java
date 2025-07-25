package com.deepanshu.simpleserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.redis.connection.RedisConfiguration;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@ComponentScan(basePackages = {
	    "com.deepanshu.simpleserver", 
	    "com.deepanshu.redisLocal",
	    "com.deepanshu.redisLocal.redisDB"
	})

	public class SimpleServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimpleServerApplication.class, args);
	}
	
	@Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    } 
}
