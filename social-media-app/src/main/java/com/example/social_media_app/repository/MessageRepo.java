package com.example.social_media_app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.social_media_app.models.Message;

public interface MessageRepo extends JpaRepository<Message, Long>{

	public List<Message> findByChatId(Long chatId);
	
}
