//package com.deepanshu.simpleserver.services.redis;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.security.crypto.bcrypt.BCrypt;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//
//import delete.RedisAuthService;
//
//@Component
//public class RedisTestRunner implements CommandLineRunner {
//
//    @Autowired
//    private RedisAuthService redisAuthService;
//
////    @Override
////    public void run(String... args) {
////        redisService.saveValue("myKey", "Hello Redis");
////        System.out.println("Value: " + redisService.getValue("myKey"));
////    }
//    public void run(String... args) {
//    	String pass = "HelloDeepanshu!123";
//    	
//    }
//    public boolean verifyUser(String username, String password) {
////    	Object auth = BCrypt.hashpw(password, BCrypt.gensalt());
////    	redisAuthService.saveAuth("Deepanshu", auth);
//    	boolean retn = redisAuthService.verifyPassword(username, password);
//    	return retn;
//    }
//}