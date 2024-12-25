package com.example.social_media_app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.social_media_app.models.Chat;
import com.example.social_media_app.models.User;
import com.example.social_media_app.request.CreateChatRequest;
import com.example.social_media_app.service.ChatService;
import com.example.social_media_app.service.UserService;

@RestController
@RequestMapping("/")
public class ChatController {

	@Autowired
	private ChatService chatService;
	
	@Autowired
	private UserService userService;

	@PostMapping("/api/chats")
	public Chat createChat(@RequestHeader("Authorization") String jwt, @RequestBody  CreateChatRequest req) {
		
		User reqUser= userService.findUserByJwt(jwt);
	
		User user2 = userService.findUserById(req.getUserId());
		
		Chat chat = chatService.createChat(reqUser, user2);
		
		return chat;
	}
	
	@GetMapping("/api/chats")
	public List<Chat> findUsersChat(@RequestHeader("Authorization") String jwt) {

		User user= userService.findUserByJwt(jwt);
		
		List<Chat> chats = chatService.findUsersChat(user.getId());
		
		return chats;
	}
 
	 
}
