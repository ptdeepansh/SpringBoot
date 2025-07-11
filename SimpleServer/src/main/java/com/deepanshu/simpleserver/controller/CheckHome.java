package com.deepanshu.simpleserver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jakarta.servlet.http.HttpSession;


@Controller
public class CheckHome {
	@GetMapping("/")
		public String UserFormPage(HttpSession session) {
//		if(session.getAttribute("user")==null) {
//			return "forward:/user/get";
//		}
	        return "forward:/index.html";
	    }
	
	}
