package com.deepanshu.simpleserver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class LoginController {
	@GetMapping("/signIn")
	public String UserFormPage() {
	        return "forward:/SignIn.html";
	}
	
	@GetMapping("/signUp")
	public String Home() {
		return "forward:/SignUp.html";
	}
	
}
