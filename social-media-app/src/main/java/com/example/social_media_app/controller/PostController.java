package com.example.social_media_app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.social_media_app.entity.Post;
import com.example.social_media_app.response.ApiResponse;
import com.example.social_media_app.service.PostService;
import com.example.social_media_app.service.UserService;

@RestController
@RequestMapping("/")
public class PostController {

	@Autowired
	PostService postService;
	
	@PostMapping("/api/posts/user/{userId}")
	public ResponseEntity<Post> createPost(@RequestBody Post post,@PathVariable Long userId){
		
		Post newPost = postService.createPost(post, userId);
		return new ResponseEntity<> (newPost,HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/api/posts/{postId}/users/{userId}")
	public ResponseEntity<ApiResponse> deletePost(@PathVariable Long postId, @PathVariable Long userId) throws Exception{
		
		String message = postService.deletePost(postId, userId);
		
		ApiResponse res = new ApiResponse(message, true);
		return new ResponseEntity<ApiResponse>(res, HttpStatus.OK);
	}
	
	@GetMapping("/api/posts/{postId}")
	public ResponseEntity<Post> findPostById(@PathVariable Long postId){
		
		Post post = postService.findPostById(postId);
		return new ResponseEntity<Post>(post,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/api/posts/user/{userId}")
	public ResponseEntity<List<Post>> findPostByUserId(@PathVariable Long userId){
	
		List<Post> posts = postService.findPostByUserId(userId);
		return new ResponseEntity<>(posts, HttpStatus.OK);
	}
	
	@GetMapping("/api/posts")
	public ResponseEntity<List<Post>> findAllPosts(){
	
		List<Post> posts = postService.findAllPost();
		return new ResponseEntity<>(posts, HttpStatus.OK);
	}
	
	
	@PutMapping("/api/posts/saved/{postId}/user/{userId}")
	public ResponseEntity<Post> savedPost(@PathVariable Long postId, @PathVariable Long userId){
		
		Post post = postService.savedPost(postId, userId);
		return new ResponseEntity<Post>(post,HttpStatus.ACCEPTED);
	}

	@PutMapping("/api/posts/liked/{postId}/user/{userId}")
	public ResponseEntity<Post> likedPost(@PathVariable Long postId, @PathVariable Long userId){
		
		Post post = postService.LikePost(postId, userId);
		return new ResponseEntity<Post>(post,HttpStatus.ACCEPTED);
	}
	
	
}
