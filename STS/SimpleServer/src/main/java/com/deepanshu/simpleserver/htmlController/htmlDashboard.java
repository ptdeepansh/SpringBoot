package com.deepanshu.simpleserver.htmlController;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.deepanshu.simpleserver.database.mongoDB.MuserData;
import com.deepanshu.simpleserver.database.mongoDB.mongoRepo;

@Controller
public class htmlDashboard {
	
	@Autowired
	private mongoRepo repo;
	
	@GetMapping("user/dashboard")
	public String userDashboard(Model model) {
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    String currentUsername = authentication.getName();

	    Optional<MuserData> optionalUser = repo.findByName(currentUsername);

	    if (optionalUser.isPresent()) {
	        MuserData user = optionalUser.get();
	        model.addAttribute("user", user);
	        model.addAttribute("title", "User Dashboard");
	        // You can add other attributes if needed
	    } else {
	        // Handle missing user gracefully
	        model.addAttribute("error", "User not found: " + currentUsername);
	    }
	    return "UserDash"; // Thymeleaf template name
	}
}