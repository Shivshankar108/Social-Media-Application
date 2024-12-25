package com.example.social_media_app.service;

import java.util.List;

import com.example.social_media_app.exceptions.ReelsException;
import com.example.social_media_app.exceptions.UserException;
import com.example.social_media_app.models.Reels;

public interface ReelsService {

	public Reels createReels(Reels reel, Long userId) throws UserException;
	
	public List<Reels> findAllReels();
	
	public List<Reels> findUsersReels(Long userId) throws UserException, ReelsException;
	
}
