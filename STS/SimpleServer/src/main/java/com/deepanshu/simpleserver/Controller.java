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
	
//	@Autowired
//	private AuthenticationManager authenticationManager;
	
//	private Map<Long, userAuthData> data = new HashMap<>();
//	@GetMapping("/get")
//	public String getAll(Model model) {
//		List<UserAuthData> users = (List<UserAuthData>) redisData.findAll();
//		model.addAttribute("users", users);
//		return "UserDetails";
//
////	    String redisUrl = "http://localhost:8080/auth"; // Change port if redis server is running on 8081
////	    ResponseEntity<List<UserAuthData>> response = restTemplate.exchange(
////	            redisUrl,
////	            HttpMethod.GET,
////	            null,
////	            new ParameterizedTypeReference<List<UserAuthData>>() {}
////	    );
////	    return response.getBody();
//	}
	
	@GetMapping("/all")
	public List<MuserData> Users(){
//		org.springframework.security.core.Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//	    String currentUsername = authentication.getName(); // default username (or email)
//	    if(currentUsername.equals("ADMIN")) {
//	    	List<MuserData> usr = repo.findAll();
//	    	return repo.findByName(currentUsername);
//	    }
//	    else {
	    	return repo.findAll();
//	    }
	}
	
//	@GetMapping("/all")
//	public ResponseEntity<?> MongoData() {
//	    if (isAdmin()) {
//	        // Admin: Return full list
//	        List<MuserData> users = repo.findAll();
//	        return ResponseEntity.ok(users);
//	    } else {
//	        // User: Return only their own data
//	        Optional<MuserData> user = repo.findByName(Cusername());
//	        return ResponseEntity.ok(user);
//	    }
//	}
//	
//	public List<MuserData> admin() {
//		return repo.findAll();
//	}
//	
//	public Optional<MuserData> user(){
//		String username = Cusername();
//		return repo.findByName(username);
//	}
//	
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

//			List<SimpleGrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(usrData.getRole()));
			
//			usrData.deepanshusetRole("Admin");
//			session.setAttribute("user", usrData.getUsername());
//			usrData.setPassword(passwordEncoder.encode(usrData.getPassword()));

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

//	@GetMapping("/{name}")
//	public ResponseEntity<UserAuthData> GetaUser(@PathVariable String name) {
//		UserAuthData user = repository.findById(name).orElse(null);
//		
//		if(user != null) {
//			return ResponseEntity.ok(user);
//		}
//		else{
//			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
//		}
//	}

	@PutMapping("/put")
	public String doPut() {
		return "200 nothing on server to put...";
	}
	
	@PostMapping("/delete/{id}")
	public String doDelete(@PathVariable String id) {
		System.out.println("Hello Ji"+ id);
		redisData.deleteById(id);
		return "The user is deleted!";
	}
	
	@GetMapping("/test")
	public String  test() {
//		RDS.run("Deepanshu");
		return "Deepanshu";
	}
	
//	@PostMapping("/g-login")
//	public ResponseEntity<?> login(@RequestBody UserAuthData usrData) {
//		System.out.println("Control Come here!");
//		boolean status = usrAuth.Login(usrData.getUsername(), usrData.getPassword());
//		try{
//			if(status) {
////				usrAuth.userDetailsService(usrData.getUsername());
//
////			UsernamePasswordAuthenticationToken authToken = 
////		            new UsernamePasswordAuthenticationToken(usrData.getUsername(), usrData.getPassword());
//////			session.setAttribute("user", usrData.getUsername());
////			
////			org.springframework.security.core.Authentication auth = authenticationManager.authenticate(authToken);
////			
////	        SecurityContextHolder.getContext().setAuthentication(auth);
////			
//////	        session.setAttribute("user", usrData.getUsername());
//			
//			return ResponseEntity.ok("Login Successful! Redirecting to /home");
//			}
//			else {
//				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials!");
//			}
//		}
//		catch(Exception ex) {
//			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials!");
//		}
//	}
	
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
		
//	@PostMapping("/test-register")
//	public ResponseEntity<?> registerTestUser() {
//	    UserAuthData testUser = new UserAuthData("testuser", "testpass", "USER");
//	    repository.save(testUser);  // IMPORTANT: This triggers indexing
//	    return ResponseEntity.ok("User saved.");
//	}
	
//	@GetMapping("/debug")
//    public ResponseEntity<?> getUserDebugInfo() {
//        org.springframework.security.core.Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String currentUsername = authentication.getName();
//
//        System.out.println("DEBUG endpoint called by user: " + currentUsername);
//
//        Optional<MuserData> optionalUser = repo.findByName(currentUsername);
//
//        if (optionalUser.isPresent()) {
//            return ResponseEntity.ok(optionalUser.get());
//        } else {
//            return ResponseEntity.status(404).body("User not found: " + currentUsername);
//        }
//    }

//	public boolean isAdmin() {
//	    return SecurityContextHolder.getContext().getAuthentication().getAuthorities()
//	            .stream()
//	            .map(GrantedAuthority::getAuthority)
//	            .anyMatch(role -> role.equals("ROLE_ADMIN")); // Note: "ROLE_" prefix is important
//	}

//	public String Cusername() {
//		org.springframework.security.core.Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		String currentUsername = authentication.getName();
//		return currentUsername;
//	}
}
