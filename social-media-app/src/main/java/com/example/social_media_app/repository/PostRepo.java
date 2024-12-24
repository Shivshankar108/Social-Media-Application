package com.example.social_media_app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.social_media_app.models.Post;

public interface PostRepo extends JpaRepository<Post, Long>{

	@Query("Select p from Post p where p.user.id=:userId")
	List<Post> findPostByUserId(Long userId);
}
