package com.example.social_media_app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.social_media_app.entity.User;
import com.example.social_media_app.entity.UserPrincipal;
import com.example.social_media_app.repository.UserRepo;


@Service
public class CustomUserDetailsService implements UserDetailsService{

	
	@Autowired
	private UserRepo userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user =userRepo.findByEmail(username);
		
		if(user == null) {
			throw new UsernameNotFoundException("User not foundwith email " + username);
		}

		
		return new UserPrincipal(user);
	}

}
