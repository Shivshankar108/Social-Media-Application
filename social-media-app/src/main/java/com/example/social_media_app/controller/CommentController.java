package com.example.social_media_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.social_media_app.models.Comment;
import com.example.social_media_app.models.User;
import com.example.social_media_app.service.CommentService;
import com.example.social_media_app.service.UserService;

import jakarta.persistence.PostRemove;

@RestController
@RequestMapping("/")
public class CommentController {

	@Autowired
	private CommentService commentService;
	
	@Autowired
	private UserService userService;
	
	
	@PostMapping("/api/post/{postId}/comments")
	public Comment createComment(@RequestHeader("Authorization") String jwt, @RequestBody Comment comment, @PathVariable Long postId) {
	
		User reqUser = userService.findUserByJwt(jwt);
		
		Comment newComment = commentService.createComment(comment, postId, reqUser.getId());
		
		return newComment;
	}
	
	
	@PutMapping("/api/comments/like/{commentId}")
	public Comment likeComment(@RequestHeader("Authorization") String jwt, @PathVariable Long commentId) throws Exception {
	
		User reqUser = userService.findUserByJwt(jwt);
		
		Comment likedComment = commentService.likeComment(commentId, reqUser.getId());
		
		return likedComment;
	}
}
