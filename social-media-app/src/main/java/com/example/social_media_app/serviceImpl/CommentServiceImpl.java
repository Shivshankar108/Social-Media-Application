package com.example.social_media_app.serviceImpl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.social_media_app.exceptions.PostException;
import com.example.social_media_app.exceptions.UserException;
import com.example.social_media_app.models.Comment;
import com.example.social_media_app.models.Post;
import com.example.social_media_app.models.User;
import com.example.social_media_app.repository.CommentRepo;
import com.example.social_media_app.repository.PostRepo;
import com.example.social_media_app.service.CommentService;
import com.example.social_media_app.service.PostService;
import com.example.social_media_app.service.UserService;


@Service
public class CommentServiceImpl implements CommentService{

	@Autowired
	private PostService postService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CommentRepo commentRepo;
	
	@Autowired
	private PostRepo postRepo;
	
	
	@Override
	public Comment createComment(Comment comment, Long postId, Long userId) throws UserException, PostException {
		
		User user = userService.findUserById(userId);
		
		Post post = postService.findPostById(postId);
		
		Comment newComment = new Comment();
		newComment.setUser(user);
		newComment.setContent(comment.getContent());
		newComment.setCreatedAt(LocalDateTime.now());
		
		post.getComments().add(newComment);
		
		postRepo.save(post);
		return commentRepo.save(newComment);
	}

	@Override
	public Comment likeComment(Long commentId, Long userId) throws UserException {
		
		Comment comment = findCommentById(commentId);
		
		User user = userService.findUserById(userId);
		
		if(comment.getLikes().contains(user)) {
			comment.getLikes().remove(user);
		}else {
			comment.getLikes().add(user);
		}
		
		return commentRepo.save(comment);
	}

	@Override
	public Comment findCommentById(Long commentId) {
		Comment comment = commentRepo.findById(commentId)
				.orElseThrow(() -> new RuntimeException("Comment not found"));
		return comment;
	}

}
