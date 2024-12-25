package com.example.social_media_app.service;

import java.util.List;

import com.example.social_media_app.exceptions.PostException;
import com.example.social_media_app.exceptions.UserException;
import com.example.social_media_app.models.Post;

public interface PostService {
	
	Post createPost(Post post,Long userId) throws UserException;
	
	String deletePost(Long postId, Long userId) throws UserException, PostException;
	
	List<Post> findPostByUserId(Long userId);
	
	Post findPostById(Long postId) throws PostException;
	
	List<Post> findAllPost();
	
	Post savedPost(Long userId, Long postId) throws PostException, UserException;
	
	Post LikePost(Long postId, Long userId) throws PostException, UserException;

}
