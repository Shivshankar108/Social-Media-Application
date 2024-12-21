package com.example.social_media_app.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

	@Id
	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	
	private List<Long> followers = new ArrayList<>();
	
	private List<Long> followings = new ArrayList<>();
}
