package com.example.social_media_app.serviceImpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.social_media_app.exceptions.ChatException;
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
		if(isExist != null) {
			return isExist;
		}
		
		Chat newChat = new Chat();
		newChat.getUsers().add(user);
		newChat.getUsers().add(reqUser);
		
		newChat.setTimeStamp(LocalDateTime.now());
		
		return chatRepo.save(newChat);
		
	}

	@Override
	public Chat findChatById(Long chatId) throws ChatException {
		
		Optional<Chat> chat = chatRepo.findById(chatId);
		
		if(chat.isEmpty()) {
			throw new ChatException("Chat Not found");
		}
		return chat.get();
	}

	@Override
	public List<Chat> findUsersChat(Long userId) {
		
		return chatRepo.findByUsersId(userId);
	}

}
