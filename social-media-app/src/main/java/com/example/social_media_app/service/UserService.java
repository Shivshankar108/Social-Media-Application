package com.example.social_media_app.service;

import java.util.List;

import com.example.social_media_app.exceptions.UserException;
import com.example.social_media_app.models.User;
import com.example.social_media_app.response.AuthResponse;

public interface UserService {

	
	public AuthResponse registerUser(User user) throws UserException;
	
	public List<User> findAll();
	
	public User findUserById(Long id) throws UserException;
	
	public User findUserByEmail(String email) throws UserException;
	
	public User followUser(Long userId1, Long userId2) throws UserException;
	
	public User updateUser(User user, Long userId) throws UserException;
	
	public List<User> searchUser(String query);
	
	public User findUserByJwt(String jwt);
	
}
