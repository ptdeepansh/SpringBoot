package com.deepanshu.simpleserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.data.redis.connection.RedisConfiguration;

@SpringBootApplication
public class SimpleServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimpleServerApplication.class, args);
	}
}
