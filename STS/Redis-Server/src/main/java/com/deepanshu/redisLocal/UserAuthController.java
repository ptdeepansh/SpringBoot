package com.deepanshu.redisLocal;

import com.deepanshu.redisLocal.redisDB.UserAuthData;
import com.deepanshu.redisLocal.redisDB.UserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class UserAuthController {

    @Autowired
    private UserAuthService userAuthService;

    @GetMapping
    public Iterable<UserAuthData> getUserAuth() {
    	   return userAuthService.getAllUsers();
    }
    
    @GetMapping("/user")
    public String us(UserAuthData uas) {
    	return uas.getUsername();
    }
}