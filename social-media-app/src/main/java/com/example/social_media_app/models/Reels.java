package com.example.social_media_app.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Reels {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long reelId;
	private String title;
	private String video;
	@ManyToOne
	private User user;
	
	private List<User> likes = new ArrayList<>();
	
	private LocalDateTime createdAt;
}
