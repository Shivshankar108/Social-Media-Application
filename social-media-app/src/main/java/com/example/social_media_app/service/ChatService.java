package com.example.social_media_app.service;

import java.util.List;

import com.example.social_media_app.models.Chat;
import com.example.social_media_app.models.User;

public interface ChatService {

	public Chat createChat(User reqUser, User user1);
	
	public Chat findChatById(Long chatId) throws Exception;
	
	public List<Chat> findUsersChat(Long userId);
	
}
