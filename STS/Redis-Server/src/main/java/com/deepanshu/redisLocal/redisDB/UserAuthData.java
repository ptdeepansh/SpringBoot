package com.deepanshu.redisLocal.redisDB;

import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.util.UUID;

//import org.springframework.stereotype.Component;
import org.springframework.data.annotation.Id;


@RedisHash("UserAuth")
public class UserAuthData {
	@Id
	private String Id;

	@Indexed
	private String username;
	private String password;
	private String role;
	
	public UserAuthData() {
	    //this.Id = UUID.randomUUID().toString();
	}
	
	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}
	
	public UserAuthData(String username, String password, String role) {
//		this.Id = Id;
		this.username = username;
		this.password = password;
		this.role = role;
	}
	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public UserAuthData(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public String getUsername() {
		return username;
	} 
	
	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPassword() {
		return password;
	}

	public Iterable<UserAuthData> getAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

		
}
