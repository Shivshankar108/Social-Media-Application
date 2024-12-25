package com.example.social_media_app.serviceImpl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.social_media_app.models.Story;
import com.example.social_media_app.models.User;
import com.example.social_media_app.repository.StoryRepo;
import com.example.social_media_app.service.StoryService;
import com.example.social_media_app.service.UserService;

@Service
public class StoryServiceImpl implements StoryService{

	@Autowired
	private StoryRepo storyRepo;
	
	@Autowired
	private UserService userService;
	
	@Override
	public Story createStory(Story story, User user) {
		
		Story newStory = new Story();
		newStory.setUser(user);
		newStory.setCaption(story.getCaption());
		newStory.setImage(story.getImage());
		newStory.setTimeStamp(LocalDateTime.now());
		return storyRepo.save(newStory);
	}

	@Override
	public List<Story> findUsersStories(Long userId) {
		
		User user = userService.findUserById(userId);
		
		return storyRepo.findByUserId(userId);
	}

}
