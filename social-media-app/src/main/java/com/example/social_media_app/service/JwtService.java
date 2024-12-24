package com.example.social_media_app.service;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
	
	private static String secretKey = "";
	
	public JwtService() {
		try {
			KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
			SecretKey sk = keyGen.generateKey();
			secretKey = Base64.getEncoder().encodeToString(sk.getEncoded());
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}

	public String generateToken(Authentication auth) {
		
		Map<String, Object> claims = new HashMap<>();
		claims.put("email", auth.getName());
		
		return Jwts.builder()
				.claims(claims)
				.subject(auth.getName())
				.issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis() + 86400000))
				.signWith(getKey())
				.compact();
				
		
	}

	private SecretKey getKey() {
		byte[] keyBytes = Decoders.BASE64.decode(secretKey);
		return Keys.hmacShaKeyFor(keyBytes);
	}

	public String extractUserName(String token) {
//		extract the username from jwt token 
		return extractClaim(token,Claims::getSubject);
	}

	private <T> T extractClaim(String token, Function<Claims,T> claimResolver) {
		final Claims claims = extractAllClaims(token);
		return claimResolver.apply(claims);
	}

	private Claims extractAllClaims(String token) {
		return Jwts.parser()
				.verifyWith(getKey())
				.build()
				.parseSignedClaims(token)
				.getPayload();
	}

	public boolean validateToken(String token, UserDetails userDetails) {
		final String userName = extractUserName(token);
		return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}

	private boolean isTokenExpired(String token) {
		
		return extractExpiration(token).before(new Date());
	}

	private Date extractExpiration(String token) {
		
		return extractClaim(token, Claims::getExpiration);
	}
	
	public String getEmailByJwt(String jwt) {
		
		if (jwt.startsWith("Bearer ")) {
	        jwt = jwt.substring(7);
	    }
		Claims claims = extractAllClaims(jwt);
		
		String email = String.valueOf(claims.get("email"));
		return email;

		
		
		
		//		System.out.println(email);
		
//		Claims claims = Jwts.parser()
//				.verifyWith(getKey())
//				.build()
////				.parseSignedClaims(jwt)
//				.parseClaimsJws(jwt)
//				.getPayload();
//		
//		String email =  String.valueOf(claims.get("email"));
//		
//		System.out.println(" before passed");
//		
//		String email = (String) extractAllClaims(jwt).get("email");
//		System.out.println("passed");
	}
	
	
}
