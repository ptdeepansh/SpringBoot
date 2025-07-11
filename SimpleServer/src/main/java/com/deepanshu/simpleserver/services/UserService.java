//package com.deepanshu.simpleserver.services;
//
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.stereotype.Service;
//
//@Service
//public class UserService {
//
//    private final RedisTemplate<String, User> userRedisTemplate;
//
//    public UserService(@Qualifier("userRedisTemplate") RedisTemplate<String, User> userRedisTemplate) {
//        this.userRedisTemplate = userRedisTemplate;
//    }
//
//    public void saveUser(User user) {
//        userRedisTemplate.opsForValue().set("user:" + user.getUsername(), user);
//    }
//
//    public User getUser(String username) {
//        return userRedisTemplate.opsForValue().get("user:" + username);
//    }
//}