package com.example.social_media_app.service;

import java.util.List;

import com.example.social_media_app.models.Reels;

public interface ReelsService {

	public Reels createReels(Reels reel, Long userId);
	
	public List<Reels> findAllReels();
	
	public List<Reels> findUsersReels(Long userId);
	
}
