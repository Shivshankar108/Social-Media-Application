package com.example.social_media_app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.social_media_app.models.Message;
import com.example.social_media_app.models.User;
import com.example.social_media_app.service.MessageService;
import com.example.social_media_app.service.UserService;

@RestController
@RequestMapping("/")
public class MessageController {

	@Autowired
	private MessageService messageService;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/api/messages/chat/{chatId}")
	public Message createMessage(@RequestHeader("Authorization") String jwt, @PathVariable Long chatId, @RequestBody Message req) throws Exception {
		
		User reqUser= userService.findUserByJwt(jwt);
		
		Message message = messageService.createMessage(reqUser, chatId, req);
		
		return message;
	}
	
	@GetMapping("/api/messages/chat/{chatId}")
	public List<Message> findChatsMessage(@RequestHeader("Authorization") String jwt, @PathVariable Long chatId) throws Exception {
		
		User reqUser= userService.findUserByJwt(jwt);
		
		List<Message> messages = messageService.findChatsMessages(chatId);
		
		return messages;
	}
	
	
}
