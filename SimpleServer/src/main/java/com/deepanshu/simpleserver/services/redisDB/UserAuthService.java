package com.deepanshu.simpleserver.services.redisDB;

import java.util.Collections;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserAuthService {
	
	@Autowired
	private final UserRepository userRepository;
	
	public UserAuthService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public UserAuthData createUser(UserAuthData user) {
		return userRepository.save(user);
	}
	
	public Optional<UserAuthData> getUser(String id){
		return userRepository.findById(id);
	}
	
	public Iterable<UserAuthData> getAllUsers(){
		return userRepository.findAll();
	}
	
	public void deleteUser(String id) {
		userRepository.deleteById(id);
	}
	
	public boolean Login(String name, String password) {
		Optional<UserAuthData> userOpt = userRepository.findById(name);
		return userOpt.isPresent() && userOpt.get().getPassword().equals(password);
	}

//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		Optional<userAuthData> user = userRepository.findByUsername(username);
//        if (user.isPresent()) {
//        	var userObj = user.get();
//        	return User.builder()
//        			.username(userObj.getUsername())
//        			.password(userObj.getPassword())
//        			.roles("USER")
//        			.build();
//        } else {
//        	throw new UsernameNotFoundException(username);
//        }
//	}
	
}
