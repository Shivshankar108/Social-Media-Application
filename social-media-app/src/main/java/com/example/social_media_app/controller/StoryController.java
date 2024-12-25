package com.example.social_media_app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.social_media_app.exceptions.UserException;
import com.example.social_media_app.models.Story;
import com.example.social_media_app.models.User;
import com.example.social_media_app.service.StoryService;
import com.example.social_media_app.service.UserService;

@RestController
@RequestMapping("/")
public class StoryController {

	@Autowired
	private StoryService storyService;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/api/newStory")
	public Story createStory(@RequestHeader("Authorization") String jwt, Story story) {
		User user = userService.findUserByJwt(jwt);
		
		return storyService.createStory(story, user);
	}
	
	@PutMapping("/api/story/user/{userId}")
	public List<Story> findAllStory(@PathVariable Long userId) throws UserException {
		
		
		return storyService.findUsersStories(userId);
	}
	
}
