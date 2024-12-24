package com.example.social_media_app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
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
	
	@GetMapping("/api/users")
	public List<User> findAllUsers(){
		
		return userService.findAll();
	}
		
	
	@GetMapping("/api/users/find/{userId}")
	public User findUserById(@PathVariable Long userId) {
		
		return userService.findUserById(userId);
	}
	
	@GetMapping("/api/users/findByEmail/{email}")
	public User findUserByEmail(@PathVariable String email) {
		return userService.findUserByEmail(email);
	}
	
	
	@PutMapping("/api/users/update")
	public User updatUserHandler(@RequestHeader("Authorization") String jwt, @RequestBody User user) throws Exception {
		
		User reqUser= userService.findUserByJwt(jwt);
		return userService.updateUser(user, reqUser.getId());
	}
	
	
	@PutMapping("/api/users/follow/{userId2}")
	public User followUserHandler(@RequestHeader("Authorization") String jwt, Long userId2) {
		
		User reqUser= userService.findUserByJwt(jwt);
		
		return userService.followUser(reqUser.getId(), userId2);
	}
	
	@GetMapping("/api/users/search")
	public List<User> searchUser(@RequestParam("query") String query){
		
		List<User> users =userService.searchUser(query);
		return users;
	}
	
	@GetMapping("/api/users/profile")
	public User getUserFromToken(@RequestHeader("Authorization") String jwt) {

		
		User user = userService.findUserByJwt(jwt);
		
		user.setPassword(null);
		return user;
	}
	
}
