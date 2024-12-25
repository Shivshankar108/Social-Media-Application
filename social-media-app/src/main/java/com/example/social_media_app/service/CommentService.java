package com.example.social_media_app.service;

import com.example.social_media_app.exceptions.PostException;
import com.example.social_media_app.exceptions.UserException;
import com.example.social_media_app.models.Comment;

public interface CommentService {

	Comment createComment(Comment comment, Long postId, Long userId) throws UserException, PostException;
	
	Comment likeComment(Long commentId, Long userId) throws UserException;
	
	Comment findCommentById(Long commentId);
}
