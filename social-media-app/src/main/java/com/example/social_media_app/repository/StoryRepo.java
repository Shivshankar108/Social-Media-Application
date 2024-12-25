package com.example.social_media_app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.social_media_app.models.Story;

public interface StoryRepo  extends JpaRepository<Story, Long>{

	
	public List<Story> findByUserId(Long userId);
	
	
}
