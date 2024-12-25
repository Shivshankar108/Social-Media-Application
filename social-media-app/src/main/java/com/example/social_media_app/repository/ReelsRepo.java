package com.example.social_media_app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.social_media_app.models.Reels;
import com.example.social_media_app.models.User;

public interface ReelsRepo  extends JpaRepository<Reels, Long>{

	public List<Reels> findByUserId(Long userId);
}
