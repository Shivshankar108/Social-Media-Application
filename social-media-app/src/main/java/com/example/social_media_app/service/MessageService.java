package com.example.social_media_app.service;

import java.util.List;

import com.example.social_media_app.models.Message;
import com.example.social_media_app.models.User;

public interface MessageService {
	
	public Message createMessage(User user, Long chatId, Message req) throws Exception;
	
	public List<Message> findChatsMessages(Long chatId) throws Exception;

}
