package com.example.social_media_app.service;

import java.util.List;

import com.example.social_media_app.models.Story;
import com.example.social_media_app.models.User;

public interface StoryService {

	public Story createStory(Story story, User user);
	
	public List<Story> findUsersStories(Long userId);
	
	
	
}
