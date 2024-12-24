package com.example.social_media_app.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.social_media_app.entity.User;
import com.example.social_media_app.repository.UserRepo;
import com.example.social_media_app.response.AuthResponse;
import com.example.social_media_app.service.JwtService;
import com.example.social_media_app.service.UserService;


@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserRepo userRepo;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	JwtService jwtService;
	
	@Override
	public AuthResponse registerUser(User user) throws Exception {
		
		User isExist = userRepo.findByEmail(user.getEmail());
		
		if(isExist != null) throw new Exception("Use different email to create account");
		
		User newUser = new User();
		newUser.setFirstName(user.getFirstName());
		newUser.setLastName(user.getLastName());
		newUser.setEmail(user.getEmail());
		newUser.setPassword(passwordEncoder.encode(user.getPassword()));
		
		User savedUser = userRepo.save(newUser);
		
		Authentication authentication = new UsernamePasswordAuthenticationToken(newUser.getEmail(), savedUser.getPassword());
		
		String token = jwtService.generateToken(authentication);
		
		AuthResponse res = new AuthResponse(token,"registered successfully");
		
		return res;
	}

	@Override
	public User findUserById(Long UserId) {
		
		User user = userRepo.findById(UserId)
				.orElseThrow(() -> new RuntimeException("Account by UserId: " + UserId + " Not Found"));
		return user;
	}

	@Override
	public User findUserByEmail(String email) {
		
		User user = userRepo.findByEmail(email);
		
		if(user == null) throw new RuntimeException("User with Email Id : " + email+ " does not exist");
		
		return user;
	}

	@Override
	public User followUser(Long userId1, Long userId2) {
		
		User user1 = findUserById(userId1);
		User user2  =findUserById(userId2);
		
		if(user1 == null || user2 == null) {
			throw new RuntimeException("User does Not exists");
		}
		else {
			user2.getFollowers().add(user1.getId());
			user1.getFollowings().add(user2.getId());

			userRepo.save(user1);
			userRepo.save(user2);
		}
		return user1;
	}

	@Override
	public User updateUser(User user, Long userId) throws Exception {
		
		User user1 = findUserById(userId);
		
		if(user1 == null) throw new Exception("User Not Exist with userId : "+ userId);
		
		if(user.getFirstName()!= null) user1.setFirstName(user.getFirstName());
		
		if(user.getLastName() != null) user1.setLastName(user.getLastName());
		
		if(user.getEmail() != null) user1.setEmail(user.getEmail());
		
		if(user.getPassword() != null) user1.setPassword(user.getPassword());
		
		return userRepo.save(user1);
	}

	@Override
	public List<User> searchUser(String query) {
		
		return userRepo.searchUser(query);
	}

}
