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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.social_media_app.entity.Post;
import com.example.social_media_app.entity.User;
import com.example.social_media_app.response.ApiResponse;
import com.example.social_media_app.service.PostService;
import com.example.social_media_app.service.UserService;

@RestController
@RequestMapping("/")
public class PostController {

	@Autowired
	PostService postService;
	
	@Autowired
	UserService userService;
	
	@PostMapping("/api/posts/user")
	public ResponseEntity<Post> createPost(@RequestHeader("Authorization") String jwt, @RequestBody Post post){
		
		User reqUser =  userService.findUserByJwt(jwt);
		
		Post newPost = postService.createPost(post, reqUser.getId());
		return new ResponseEntity<> (newPost,HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/api/posts/{postId}/delete")
	public ResponseEntity<ApiResponse> deletePost(@RequestHeader("Authorization") String jwt, @PathVariable Long postId) throws Exception{

		User reqUser =  userService.findUserByJwt(jwt);
		
		String message = postService.deletePost(postId, reqUser.getId());
		
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
	
	
	@PutMapping("/api/posts/saved/{postId}")
	public ResponseEntity<Post> savedPost(@RequestHeader("Authorization") String jwt, @PathVariable Long postId){

		User reqUser =  userService.findUserByJwt(jwt);
		
		Post post = postService.savedPost(postId, reqUser.getId());
		return new ResponseEntity<Post>(post,HttpStatus.ACCEPTED);
	}

	@PutMapping("/api/posts/liked/{postId}/user/{userId}")
	public ResponseEntity<Post> likedPost(@RequestHeader("Authorization") String jwt, @PathVariable Long postId){

		User reqUser =  userService.findUserByJwt(jwt);
		
		Post post = postService.LikePost(postId, reqUser.getId());
		return new ResponseEntity<Post>(post,HttpStatus.ACCEPTED);
	}
	
	
}
