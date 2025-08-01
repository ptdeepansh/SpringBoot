package com.deepanshu.simpleserver;

import java.util.*;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.deepanshu.simpleserver.database.mongoDB.MuserData;
import com.deepanshu.simpleserver.database.mongoDB.mongoRepo;
import com.deepanshu.redisLocal.redisDB.UserRepository;
import com.deepanshu.redisLocal.redisDB.UserAuthData;
import com.deepanshu.redisLocal.redisDB.UserAuthService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
public class Controller {
	
	@Autowired
	private UserRepository redisData;
	
	@Autowired
	private UserAuthService userAuth;
	
	@Autowired
	private mongoRepo repo;
	
	@Autowired
    private RestTemplate restTemplate;
	
	@PostMapping("/post")
	public ResponseEntity<UserAuthData> StoreData(@RequestBody UserAuthData usrData, HttpSession session) {
		String name = usrData.getUsername();
			Optional<MuserData> optionalUser = repo.findByName(name);
		if (!optionalUser.isPresent()) {
			MuserData user = new MuserData();
			user.setName(name);
			user.setState("Delhi");
			user.setTotalmatches(0);
			user.setTotalkills(0);
			user.setPhone_no(12);
			user.setEmail_id(null);
			user.setState("Delhi");
			user.setAge(null);
			user.setJoined_date(new Date());
			repo.save(user);
			System.out.println("User Created successfully");
		}
		try {
		System.out.println("Name: "+usrData.getUsername()+ "\nPassword: "+usrData.getPassword()+ usrData.getRole());
		
        if (usrData.getRole() == null) {
            usrData.setRole("USER");
        }
		return ResponseEntity.ok(userAuth.createUser(usrData));
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			return ResponseEntity.ok(userAuth.createUser(usrData));
		}
	}

	@PutMapping("/put")
	public String doPut() {
		return "200 nothing on server to put...";
	}
	
	@PostMapping("/delete/{id}/{username}")
	public String doDelete(@PathVariable String id, @PathVariable String username) {
		System.out.println("Hello Ji"+ id);
		redisData.deleteById(id); // Delete from REDIS
		//repo.deleteByName(username); // Delete from MONGODB
		Optional<MuserData> user = repo.findByName(username);
		System.out.println(user.isPresent() + username);
	    if (user.isPresent()) {
	    	repo.deleteById(user.get().getId());
	        System.out.println("✅ MongoDB user deleted");
	    } else {
	        System.out.println("❌ MongoDB user not found with name: " + username);
	    }

		return "GG";
	}
	
	@PostMapping("/update")
	public ResponseEntity<?> updateUser(@RequestParam String name,
            @RequestParam String email_id,
            @RequestParam Long phone_no,
            @RequestParam int age,
            RedirectAttributes redirectAttributes) {
			Optional<MuserData> optionalUser = repo.findByName(name);
			if (optionalUser.isPresent()) {
				MuserData user = optionalUser.get();
				user.setEmail_id(email_id);
				user.setPhone_no(phone_no);
				user.setAge(age);
				repo.save(user);
				redirectAttributes.addFlashAttribute("message", "Details updated successfully!");
			}
			redirectAttributes.addFlashAttribute("error", "User not found!");
			return ResponseEntity.ok("User updated successfully!");
	}
	
	@GetMapping("/home")
	public String homepage() {
		return "index";
	}
}
