package com.example.social_media_app.serviceImpl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.social_media_app.models.Reels;
import com.example.social_media_app.models.User;
import com.example.social_media_app.repository.ReelsRepo;
import com.example.social_media_app.service.ReelsService;
import com.example.social_media_app.service.UserService;

@Service
public class ReelsServiceImpl  implements ReelsService{

	@Autowired
	private ReelsRepo reelsRepo;
	
	@Autowired
	UserService userService;
	
	@Override
	public Reels createReels(Reels reel, Long userId) {
		
		User user = userService.findUserById(userId);
		
		Reels newReel =new Reels();
		newReel.setTitle(reel.getTitle());
		newReel.setUser(user);
		newReel.setVideo(reel.getVideo());
		newReel.setCreatedAt(LocalDateTime.now());
		
		return reelsRepo.save(newReel);
	}

	@Override
	public List<Reels> findAllReels() {
		return reelsRepo.findAll();
	}

	@Override
	public List<Reels> findUsersReels(Long userId) {
		
		User user = userService.findUserById(userId);
		if(user == null )throw new RuntimeException("User Not Found");
		return reelsRepo.findByUserId(userId);
	}

	
}
