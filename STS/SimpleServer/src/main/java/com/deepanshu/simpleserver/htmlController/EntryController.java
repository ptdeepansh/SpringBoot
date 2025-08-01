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
import com.deepanshu.simpleserver.database.mongoDB.MuserData;
import com.deepanshu.simpleserver.database.mongoDB.mongoRepo;

import jakarta.servlet.http.HttpServletRequest;

//import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;


@Controller
public class EntryController {
	

	@Autowired
	private UserRepository redisData;

	@Autowired
	private mongoRepo Mrepo;
	
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
	
	// Giving access by ADMIN only
	
	@GetMapping("/authPerson")
	public String AuthPerson(Model model) {
		if(isAdmin()) {
			System.out.println("Authorize");
			return "forward:/addData.html";	
		}
		else {
			System.out.println("Unauthorize");
			model.addAttribute("Value", "401 - Forbidden");
			return "404";
		}
	}
	
	// Function to check is the current user is ADMIN
	
	public boolean isAdmin() {
	    return SecurityContextHolder.getContext().getAuthentication().getAuthorities()
	            .stream()
	            .map(GrantedAuthority::getAuthority)
	            .anyMatch(role -> role.equals("ROLE_ADMIN")); // Note: "ROLE_" prefix is important
	}
	
	// Getting user data from REDIS
	
	@GetMapping("/user/get")
	public String getAll(Model model) {
		if(isAdmin()) {
			List<UserAuthData> users = (List<UserAuthData>) redisData.findAll();
			model.addAttribute("users", users);
		}
		else {
			org.springframework.security.core.Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		    String currentUsername = authentication.getName(); // Get USERNAME
		    Optional<UserAuthData> usersOpt = redisData.findByUsername(currentUsername);
	    	List<UserAuthData> users = usersOpt.map(List::of).orElse(List.of());
	    	model.addAttribute("users", users);
		}
		return "UserDetails";
	
	}
	
	// Getting user data from MongoDB
	
	@GetMapping("/user/all")
	public String mongoDb(Model model) {
		org.springframework.security.core.Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    String currentUsername = authentication.getName(); // Get USERNAME
	    if(authentication.getAuthorities().stream()
	            .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
	    	List<MuserData> usr = Mrepo.findAll();
	    	model.addAttribute("users",usr);
	    }
	    else {
	    	Optional<MuserData> usr = Mrepo.findByName(currentUsername);
	    	model.addAttribute("users",
	    		    Mrepo.findByName(currentUsername)
	    		         .map(List::of)
	    		         .orElse(List.of())
	    		);
	    }
		return "MongoDBuser";
	}
}
