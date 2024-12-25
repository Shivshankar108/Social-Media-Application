package com.example.social_media_app.serviceImpl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.social_media_app.models.Chat;
import com.example.social_media_app.models.User;
import com.example.social_media_app.repository.ChatRepo;
import com.example.social_media_app.service.ChatService;

@Service
public class ChatServiceImpl implements ChatService{

	@Autowired
	ChatRepo chatRepo;
	
	
	@Override
	public Chat createChat(User reqUser, User user) {
		
		Chat isExist = chatRepo.findChatByUsersId(user, reqUser);
		if(isExist != null) return isExist;
		
		Chat newChat = new Chat();
		newChat.getUsers().add(user);
		newChat.getUsers().add(reqUser);
		
		newChat.setTimeStamp(LocalDateTime.now());
		
		return chatRepo.save(newChat);
		
	}

	@Override
	public Chat findChatById(Long chatId) throws Exception {
		
		Chat chat = chatRepo.findById(chatId)
				.orElseThrow(()-> new Exception("Chat not found"));
		return chat;
	}

	@Override
	public List<Chat> findUsersChat(Long userId) {
		
		return chatRepo.findByUserId(userId);
	}

}
