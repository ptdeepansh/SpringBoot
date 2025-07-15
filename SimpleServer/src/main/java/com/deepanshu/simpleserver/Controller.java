package com.deepanshu.simpleserver;

import java.util.ArrayList;

import java.util.*;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import org.springframework.security.core.context.SecurityContextHolder;

import com.deepanshu.simpleserver.database.mongoDB.MuserData;
import com.deepanshu.simpleserver.database.mongoDB.mongoRepo;
import com.deepanshu.simpleserver.database.redisDB.UserAuthData;
import com.deepanshu.simpleserver.database.redisDB.UserAuthService;
import com.deepanshu.simpleserver.database.redisDB.UserRepository;

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
	public Optional<MuserData> Users(){
		org.springframework.security.core.Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    String currentUsername = authentication.getName(); // default username (or email)
	    if(currentUsername.equals("ADMIN")) {
//	    	List<MuserData> usr = repo.findAll();
	    	AdUser();
	    	return repo.findByName(currentUsername);
	    }
	    else {
	    	return repo.findByName(currentUsername);
	    }
	}
	@GetMapping("GT")
	public List<MuserData> AdUser(){
		org.springframework.security.core.Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentUsername = authentication.getName(); // default username (or email)
		return repo.findAll();
	}
	
		@PostMapping("/post")
		public ResponseEntity<UserAuthData> getResourceUri(@RequestBody UserAuthData usrData, HttpSession session) {
			try {
			System.out.println("Name: "+usrData.getUsername()+ "Password: "+usrData.getPassword()+ usrData.getRole());

//			List<SimpleGrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(usrData.getRole()));
			
//			usrData.deepanshusetRole("Admin");
//			session.setAttribute("user", usrData.getUsername());
//			usrData.setPassword(passwordEncoder.encode(usrData.getPassword()));

	        if (usrData.getRole() == null) {
	            usrData.setRole("USER");
	        }
			
			return ResponseEntity.ok(usrAuth.createUser(usrData));
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
//				usrAuth.userDetailsService(usrData.getUsername());

//			UsernamePasswordAuthenticationToken authToken = 
//		            new UsernamePasswordAuthenticationToken(usrData.getUsername(), usrData.getPassword());
////			session.setAttribute("user", usrData.getUsername());
//			
//			org.springframework.security.core.Authentication auth = authenticationManager.authenticate(authToken);
//			
//	        SecurityContextHolder.getContext().setAuthentication(auth);
//			
////	        session.setAttribute("user", usrData.getUsername());
			
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
		return "index";
	}
	
	@PostMapping("/test-register")
	public ResponseEntity<?> registerTestUser() {
	    UserAuthData testUser = new UserAuthData("testuser", "testpass", "USER");
	    repository.save(testUser);  // IMPORTANT: This triggers indexing
	    return ResponseEntity.ok("User saved.");
	}
	
	@GetMapping("/debug")
    public ResponseEntity<?> getUserDebugInfo() {
        org.springframework.security.core.Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();

        System.out.println("DEBUG endpoint called by user: " + currentUsername);

        Optional<MuserData> optionalUser = repo.findByName(currentUsername);

        if (optionalUser.isPresent()) {
            return ResponseEntity.ok(optionalUser.get());
        } else {
            return ResponseEntity.status(404).body("User not found: " + currentUsername);
        }
    }


}
