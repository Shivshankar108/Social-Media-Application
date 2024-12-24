package com.example.social_media_app.service;

import java.util.List;

import com.example.social_media_app.models.User;
import com.example.social_media_app.response.AuthResponse;

public interface UserService {

	
	public AuthResponse registerUser(User user) throws Exception;
	
	public List<User> findAll();
	
	public User findUserById(Long id);
	
	public User findUserByEmail(String email);
	
	public User followUser(Long userId1, Long userId2);
	
	public User updateUser(User user, Long userId) throws Exception;
	
	public List<User> searchUser(String query);
	
	public User findUserByJwt(String jwt);
	
}
