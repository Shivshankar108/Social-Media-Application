package com.example.social_media_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.social_media_app.models.Comment;

public interface CommentRepo extends JpaRepository<Comment, Long>{

}
