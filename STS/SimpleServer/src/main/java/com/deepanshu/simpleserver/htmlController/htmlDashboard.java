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
//
////import ch.qos.logback.core.model.Model;
//
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
//
////	
////	@GetMapping("/user/dashboard")
////	public Optional<MuserData> UsrDashhtml(Model model, mongoRepo repo) {
////		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
////	    String currentUsername = authentication.getName(); // default username (or email)
//////		MuserData user = new MuserData("Deepanshu", 23);
//////		String myName = "Deepanshu";
////		return repo.findByName(currentUsername);
//////		if (users.isPresent()) {
//////	        MuserData user = users.get();
//////
//////	        // Add user object to model
//////	        model.addAttribute("user", user);
//////	        model.addAttribute("name", user.getName());
//////	        model.addAttribute("erp", "0221BCA147");  // You can map this from user if applicable
//////	        model.addAttribute("contactNo", user.getPhone_no());
//////	    } else {
//////	        model.addAttribute("error", "User not found");
//////	        return "errorPage"; // make sure this exists
//////	    }
//////		
////////		model.addAttribute("user", users);
//////		model.addAttribute("name", "Deepansh");
//////		model.addAttribute("erp", "0221BCA147");
//////		model.addAttribute("contactNo", "9891567928");
//////		model.addAttribute("title", "User");
////////		model.addAllAttributes(repo.findAll());
//////		return "UserDash";
////	}
//	
//	@GetMapping("/admin/dashbord")
//	public String UsrDashhtml1(Model model) {
//		String name = "Deepansh";
//		model.addAttribute("name", "Deepansh");
//		model.addAttribute("erp", "0221BCA147");
//		model.addAttribute("contactNo", "9891567928");
//		model.addAttribute("title", "Admin");
//		return "AdminDash";
//	}
//}
