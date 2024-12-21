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
import com.example.social_media_app.service.UserService;

@RestController
@RequestMapping("/")
public class UserController {

	@Autowired
	UserRepo userRepo;
	
	@Autowired
	UserService userService;
	
	@PostMapping("/users")
	public User createUser(@RequestBody User user) {
		return userService.registerUser(user);
	}
	
	
	@GetMapping("/users/{userId}")
	public User findUserById(@PathVariable Long userId) {
		
		return userService.findUserById(userId);
	}
	
	@PutMapping("/users/follow/{userId1}/{userId2}")
	public User followUserHandler(@PathVariable Long userId1, Long userId2) {
		
		return userService.followUser(userId1, userId2);
	}
	
	@GetMapping("/users/search")
	public List<User> searchUser(@RequestParam("query") String query){
		
		List<User> users =userService.searchUser(query);
		return users;
	}
	
}
