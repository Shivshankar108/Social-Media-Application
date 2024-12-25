package com.example.social_media_app.service;

import com.example.social_media_app.models.Comment;

public interface CommentService {

	Comment createComment(Comment comment, Long postId, Long userId);
	
	Comment likeComment(Long commentId, Long userId) throws Exception;
	
	Comment findCommentById(Long commentId);
}
