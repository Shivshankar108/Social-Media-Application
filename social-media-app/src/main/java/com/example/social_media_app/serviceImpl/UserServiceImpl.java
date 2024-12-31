package com.example.social_media_app.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.social_media_app.config.JwtFilter;
import com.example.social_media_app.exceptions.UserException;
import com.example.social_media_app.models.User;
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
	public AuthResponse registerUser(User user) throws UserException {
		
		User isExist = userRepo.findByEmail(user.getEmail());
		
		if(isExist != null) throw new UserException("Use different email to create account");
		
		User newUser = new User();
		newUser.setFirstName(user.getFirstName());
		newUser.setLastName(user.getLastName());
		newUser.setEmail(user.getEmail());
		newUser.setGender(user.getGender());
		newUser.setPassword(passwordEncoder.encode(user.getPassword()));
		
		User savedUser = userRepo.save(newUser);
		
		Authentication authentication = new UsernamePasswordAuthenticationToken(newUser.getEmail(), savedUser.getPassword());
		
		String token = jwtService.generateToken(authentication);
		
		AuthResponse res = new AuthResponse(token,"registered successfully");
		
		return res;
	}
	
	@Override
	public List<User> findAll() {

		List<User> users = userRepo.findAll();
		
		return users;
	}
	

	@Override
	public User findUserById(Long UserId) throws UserException {
		
		User user = userRepo.findById(UserId)
				.orElseThrow(() -> new UserException("Account by UserId: " + UserId + " Not Found"));
		return user;
	}

	@Override
	public User findUserByEmail(String email) throws UserException {
		
		User user = userRepo.findByEmail(email);
		
		if(user == null) throw new UserException("User with Email Id : " + email+ " does not exist");
		
		return user;
	}

	@Override
	public User followUser(Long reqUserId, Long userId2) throws UserException {
		
		User reqUser = findUserById(reqUserId);
		User user2  =findUserById(userId2);
		
		if(reqUser == null || user2 == null) {
			throw new RuntimeException("User does Not exists");
		}
		else {
			user2.getFollowers().add(reqUser.getId());
			reqUser.getFollowings().add(user2.getId());

			userRepo.save(reqUser);
			userRepo.save(user2);
		}
		return reqUser;
	}

	@Override
	public User updateUser(User user, Long userId) throws UserException {
		
		User user1 = findUserById(userId);
		
		if(user1 == null) throw new UserException("User Not Exist with userId : "+ userId);
		
		if(user.getFirstName()!= null) user1.setFirstName(user.getFirstName());
		
		if(user.getLastName() != null) user1.setLastName(user.getLastName());
		
		if(user.getEmail() != null) user1.setEmail(user.getEmail());
		
		if(user.getGender() != null) user1.setGender(user.getGender());
		
		if(user.getPassword() != null) user1.setPassword(user.getPassword());
		
		return userRepo.save(user1);
	}

	@Override
	public List<User> searchUser(String query) {
		
		return userRepo.searchUser(query);
	}

	@Override
	public User findUserByJwt(String jwt) {
		
		String email = jwtService.getEmailByJwt(jwt);
		
		User user = userRepo.findByEmail(email);
		if(user == null) System.out.println("user not found");
		
		return user;
	}

	 

}
