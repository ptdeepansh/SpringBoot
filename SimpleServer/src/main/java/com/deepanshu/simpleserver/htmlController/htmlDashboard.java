package com.deepanshu.simpleserver.htmlController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.deepanshu.simpleserver.services.mongoDB.MuserData;
import com.deepanshu.simpleserver.services.mongoDB.mongoRepo;

//import ch.qos.logback.core.model.Model;

@Controller
public class htmlDashboard {
	
	@Autowired
	private mongoRepo repo;
	
	
	@GetMapping("/user/dashbord")
	public String Dashhtml(Model model) {
//		MuserData user = new MuserData("Deepanshu", 23);
		String myName = "Deepanshu";
		model.addAttribute("hello", "Deepansh");
		model.addAttribute("myNameValue", myName);
		model.addAllAttributes(repo.findAll());
		return "dashboard";
	}
}
