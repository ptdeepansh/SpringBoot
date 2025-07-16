package com.deepanshu.simpleserver.htmlController;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.deepanshu.simpleserver.database.mongoDB.MuserData;
import com.deepanshu.simpleserver.database.mongoDB.mongoRepo;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;


@Controller
public class EntryController {
	
	// Redirecting to the user login window
	
	@GetMapping("/signIn")
	public String UserFormPage(HttpServletRequest request) {
		String successParam = request.getParameter("success");
		if("true".equals(successParam)) {
			return "forward:/SignIn.html";
		}
		else {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			if (auth != null && auth.isAuthenticated() && !(auth instanceof AnonymousAuthenticationToken)) {
	        return "index";  // Already logged in (redirect:/)
			}
		}
	    return "forward:/SignIn.html"; // return the login HTML view name
	}
	
	// Redirecting to sign up window
	
	@GetMapping("/signUp")
	public String RegisterForm() {
		return "forward:/SignUp.html";
	}
	
	// Redirecting to the logout
	
	@GetMapping("/logout")
	public String Logout() {
		return "redirect:/signIn/login?logout=true";
	}
	
	// Redirecting to the Home
	
	@GetMapping("/home")
	public String Home() {
		return "redirect:/";
	}
	
	@GetMapping("/authPerson")
	public String AuthPerson(Model model) {
		if(isAdmin()) {
			return "forward:/addData.html";	
		}
		else {
			model.addAttribute("Value", "401 - Forbidden");
			return "404";
		}
	}
	public boolean isAdmin() {
	    return SecurityContextHolder.getContext().getAuthentication().getAuthorities()
	            .stream()
	            .map(GrantedAuthority::getAuthority)
	            .anyMatch(role -> role.equals("ROLE_ADMIN")); // Note: "ROLE_" prefix is important
	}
	
}
