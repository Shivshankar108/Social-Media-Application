package com.example.social_media_app.service;

import java.util.List;

import com.example.social_media_app.entity.User;

public interface UserService {

	
	public User registerUser(User user);
	
	public User findUserById(Long id);
	
	public User findUserByEmail(String email);
	
	public User followUser(Long userId1, Long userId2);
	
	public User updateUser(User user, Long userId) throws Exception;
	
	
	public List<User> searchUser(String query);
	
}
