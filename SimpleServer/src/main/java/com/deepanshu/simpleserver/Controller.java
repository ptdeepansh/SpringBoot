package com.deepanshu.simpleserver;

import java.util.ArrayList;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import com.deepanshu.simpleserver.services.mongoDB.MuserData;
import com.deepanshu.simpleserver.services.mongoDB.mongoRepo;
import com.deepanshu.simpleserver.services.redisDB.UserAuthService;
import com.deepanshu.simpleserver.services.redisDB.UserRepository;
import com.deepanshu.simpleserver.services.redisDB.UserAuthData;

import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;

@RestController
@RequestMapping("/user")
public class Controller {
	@Autowired
//	private RedisTestRunner RDS;
	
	private UserAuthService usrAuth;
	
//	@Autowired
//	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private mongoRepo repo;
	
//	@Autowired
//	private AuthenticationManager authenticationManager;
	
//	private Map<Long, userAuthData> data = new HashMap<>();
	@GetMapping("/get")
	public Iterable<UserAuthData> getAll(){
		return usrAuth.getAllUsers();
	}
	
	@GetMapping("/all")
	public List<MuserData> addUser(){
		return repo.findAll();
	}
	
		@PostMapping("/post")
		public ResponseEntity<UserAuthData> getResourceUri(@RequestBody UserAuthData usrData, HttpSession session) {
			try {
			System.out.println("Name: "+usrData.getUsername()+ "Password: "+usrData.getPassword());
	//		List<SimpleGrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(usrData.getRole()));
			
	//		usrData.setRole("Admin");
//			session.setAttribute("user", usrData.getUsername());
//			usrData.setPassword(passwordEncoder.encode(usrData.getPassword()));

	        // Optional: assign default role
	        if (usrData.getRole() == null) {
	            usrData.setRole("USER");
	        }
			
			return ResponseEntity.ok(usrAuth.createUser(usrData));
			//		data.(v1.getId(), v1);
	//		return true;
			}
			catch(Exception ex) {
				System.out.println(ex.getMessage());
				return ResponseEntity.ok(usrAuth.createUser(usrData));
			}
		}
	@GetMapping("/{name}")
	public ResponseEntity<UserAuthData> GetaUser(@PathVariable String name) {
		UserAuthData user = repository.findById(name).orElse(null);
		
		if(user != null) {
			return ResponseEntity.ok(user);
		}
		else{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
	@PutMapping("/put")
	public String doPut() {
		return "200 nothing on server to put...";
	}
	
	@DeleteMapping("/delete")
	public String doDelete() {
		return "200 nothing to delete on server...";
	}
	@GetMapping("/test")
	public String  test() {
//		RDS.run("Deepanshu");
		return "Deepanshu";
	}
	@PostMapping("/g-login")
	public ResponseEntity<?> login(@RequestBody UserAuthData usrData) {
		System.out.println("Control Come here!");
		boolean status = usrAuth.Login(usrData.getUsername(), usrData.getPassword());
		try{
			if(status) {

//			UsernamePasswordAuthenticationToken authToken = 
//		            new UsernamePasswordAuthenticationToken(usrData.getUsername(), usrData.getPassword());
//			session.setAttribute("user", usrData.getUsername());
//			
//			Authentication auth = authenticationManager.authenticate(authToken);
//			
//	        SecurityContextHolder.getContext().setAuthentication(auth);
//			
//	        session.setAttribute("user", usrData.getUsername());
			
			return ResponseEntity.ok("Login Successful! Redirecting to /home");
			}
			else {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials!");
			}
		}
		catch(Exception ex) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials!");
		}
	}
	
	@GetMapping("/home")
	public String homepage() {
		return "forwad:/index.html";
	}
	
	@PostMapping("/test-register")
	public ResponseEntity<?> registerTestUser() {
	    UserAuthData testUser = new UserAuthData("testuser", "testpass", "user");
	    repository.save(testUser);  // IMPORTANT: This triggers indexing
	    return ResponseEntity.ok("User saved.");
	}

}
