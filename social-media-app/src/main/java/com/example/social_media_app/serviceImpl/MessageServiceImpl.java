package com.example.social_media_app.serviceImpl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.social_media_app.models.Chat;
import com.example.social_media_app.models.Message;
import com.example.social_media_app.models.User;
import com.example.social_media_app.repository.ChatRepo;
import com.example.social_media_app.repository.MessageRepo;
import com.example.social_media_app.service.ChatService;
import com.example.social_media_app.service.MessageService;

@Service
public class MessageServiceImpl implements MessageService{

	@Autowired
	private MessageRepo messageRepo;
	
	@Autowired
	private ChatService chatService;
	
	@Autowired
	private ChatRepo chatRepo;

	@Override
	public Message createMessage(User user, Long chatId, Message req) throws Exception {
		
		Chat chat= chatService.findChatById(chatId);
		
		Message message = new Message();
		message.setChat(chat);
		message.setContent(req.getContent());
		message.setImage(req.getImage());
		message.setUser(user);
		message.setTimestamp(LocalDateTime.now());
		
		Message savedMessage =messageRepo.save(message);
		chat.getMessages().add(message);
		chatRepo.save(chat);
				
		return savedMessage;
	}

	@Override
	public List<Message> findChatsMessages(Long chatId) throws Exception {
		
		Chat chat= chatService.findChatById(chatId);
		
		return messageRepo.findByChatId(chatId);
	}
	
	
	
}
