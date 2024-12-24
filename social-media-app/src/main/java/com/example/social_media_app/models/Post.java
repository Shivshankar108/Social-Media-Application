package com.example.social_media_app.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "posts")
@Data
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long postId;
	private String caption;
	private String image;
	private String video;
	private LocalDateTime createdAt;
	
	@ManyToOne
	private User user;
	
	@OneToMany
	private List<User> liked = new ArrayList<>();
	
}
