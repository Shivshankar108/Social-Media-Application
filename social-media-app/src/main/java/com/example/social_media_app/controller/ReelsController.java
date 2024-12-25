package com.example.social_media_app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.social_media_app.exceptions.ReelsException;
import com.example.social_media_app.exceptions.UserException;
import com.example.social_media_app.models.Reels;
import com.example.social_media_app.models.User;
import com.example.social_media_app.service.ReelsService;
import com.example.social_media_app.service.UserService;

@RestController
@RequestMapping("/")
public class ReelsController {

	@Autowired
	private ReelsService reelsService;
	
	@Autowired
	private UserService userService;
	
	
	@PostMapping("/api/createReel")
	public Reels CreateReels(@RequestHeader("Authorization") String jwt, @RequestBody Reels reel) throws UserException {
		
		User user = userService.findUserByJwt(jwt);
		Reels newReel = reelsService.createReels(reel, user.getId());
		return newReel;
	}
	
	@GetMapping("/api/reels")
	public List<Reels> findAllReel() {
		
		return reelsService.findAllReels();
	}
	
	@GetMapping("/api/reels/user/{userId}")
	public List<Reels> findUsersReels(@PathVariable Long userId) throws UserException, ReelsException {
		
		List<Reels> reels = reelsService.findUsersReels(userId);
		
		return reels;
	}
	
}
