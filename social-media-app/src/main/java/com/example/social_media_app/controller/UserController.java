package com.example.social_media_app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.social_media_app.entity.User;
import com.example.social_media_app.repository.UserRepo;
import com.example.social_media_app.response.AuthResponse;
import com.example.social_media_app.service.UserService;

@RestController
@RequestMapping("/")
public class UserController {

	@Autowired
	UserRepo userRepo;
	
	@Autowired
	UserService userService;
		
	
	@GetMapping("/api/users/find/{userId}")
	public User findUserById(@PathVariable Long userId) {
		
		return userService.findUserById(userId);
	}
	
	@GetMapping("/api/users/findByEmail/{email}")
	public User findUserByEmail(@PathVariable String email) {
		return userService.findUserByEmail(email);
	}
	
	
	@PutMapping("/api/users/update/{userId}")
	public User updatUserHandler(@RequestBody User user, @PathVariable Long userId) throws Exception {
		return userService.updateUser(user, userId);
	}
	
	
	@PutMapping("/api/users/follow/{userId1}/{userId2}")
	public User followUserHandler(@PathVariable Long userId1, Long userId2) {
		
		return userService.followUser(userId1, userId2);
	}
	
	@GetMapping("/api/users/search")
	public List<User> searchUser(@RequestParam("query") String query){
		
		List<User> users =userService.searchUser(query);
		return users;
	}
	
}
