package com.example.social_media_app.service;

import java.util.List;

import com.example.social_media_app.entity.Post;

public interface PostService {
	
	Post createPost(Post post,Long userId);
	
	String deletePost(Long postId, Long userId) throws Exception;
	
	List<Post> findPostByUserId(Long userId);
	
	Post findPostById(Long postId);
	
	List<Post> findAllPost();
	
	Post savedPost(Long userId, Long postId);
	
	Post LikePost(Long postId, Long userId);

}
