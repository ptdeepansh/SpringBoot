package com.deepanshu.simpleserver.htmlController;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import com.deepanshu.redisLocal.redisDB.UserAuthData;
import com.deepanshu.redisLocal.redisDB.UserRepository;
//import com.deepanshu.redisLocal.redisDB.UserRepository;
//import com.deepanshu.simpleserver.database.mongoDB.TempAuthData;
//import com.deepanshu.simpleserver.database.mongoDB.TempUserAuthData;

import jakarta.servlet.http.HttpServletRequest;

//import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;


@Controller
public class EntryController {
	

	@Autowired
	private UserRepository redisData;
//	
//	@Autowired
//	private RestTemplate rp;
//	
//	// Redirecting to the user login window
//	
	@GetMapping("/signIn")
	public String UserFormPage(HttpServletRequest request) {

//		tp.saveAll(rp.getForObject("localhost:8080/auth", Iterable.class));
//		System.out.print(tp.findAll());
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
//		if(isAdmin()) {
			return "forward:/addData.html";	
//		}
//		else {
//			model.addAttribute("Value", "401 - Forbidden");
//			return "404";
//		}
	}
//	public boolean isAdmin() {
//	    return SecurityContextHolder.getContext().getAuthentication().getAuthorities()
//	            .stream()
//	            .map(GrantedAuthority::getAuthority)
//	            .anyMatch(role -> role.equals("ROLE_ADMIN")); // Note: "ROLE_" prefix is important
//	}
//	
	@GetMapping("/user/get")
	public String getAll(Model model) {
		List<UserAuthData> users = (List<UserAuthData>) redisData.findAll();
		model.addAttribute("users", users);
		return "UserDetails";
	
	}
}
