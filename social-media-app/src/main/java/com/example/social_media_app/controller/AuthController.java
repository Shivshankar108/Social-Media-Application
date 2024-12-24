package com.example.social_media_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authorization.AuthenticatedAuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.social_media_app.models.User;
import com.example.social_media_app.repository.UserRepo;
import com.example.social_media_app.request.LoginRequest;
import com.example.social_media_app.response.AuthResponse;
import com.example.social_media_app.service.CustomUserDetailsService;
import com.example.social_media_app.service.JwtService;
import com.example.social_media_app.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;
    
    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    // Signup endpoint
    @PostMapping("/signup")
    public AuthResponse createUser(@RequestBody User user) throws Exception {
        
        // Check if the user already exists
        User isExist = userRepo.findByEmail(user.getEmail());
        
        if (isExist != null) {
            // If user exists, throw custom exception (you can create a custom exception for this)
            throw new Exception("Use a different email to create an account");
        }

        // Create and save new user
        User newUser = new User();
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(passwordEncoder.encode(user.getPassword())); // Encrypt password
        
        // Save new user to the database
        User savedUser = userRepo.save(newUser);

        // Authenticate the newly created user
        Authentication authentication = new UsernamePasswordAuthenticationToken(newUser.getEmail(), savedUser.getPassword());

        // Generate JWT token after user authentication
        String token = jwtService.generateToken(authentication);

        // Return response with token and success message
        AuthResponse res = new AuthResponse(token, "Registered successfully");

        return res;
    }
    
    
    @PostMapping("/signin")
    public AuthResponse signin(@RequestBody LoginRequest loginRequest) {
    	
    	Authentication authentication = Authenticate(loginRequest.getEmail(), loginRequest.getPassword());
    	
    	// Generate JWT token after user authentication
        String token = jwtService.generateToken(authentication);

        // Return response with token and success message
        AuthResponse res = new AuthResponse(token, "Logged in successfully");

        return res;
    }

	private Authentication Authenticate(String email, String password) {
		
		UserDetails userDetails = customUserDetailsService.loadUserByUsername(email);
		
		if(userDetails == null || !passwordEncoder.matches(password, userDetails.getPassword())) {
			throw new BadCredentialsException("Invalid username or password");
		}
		
		
		return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
	}
    
}
