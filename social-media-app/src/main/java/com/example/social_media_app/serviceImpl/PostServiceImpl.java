package com.example.social_media_app.serviceImpl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.social_media_app.exceptions.PostException;
import com.example.social_media_app.exceptions.UserException;
import com.example.social_media_app.models.Post;
import com.example.social_media_app.models.User;
import com.example.social_media_app.repository.PostRepo;
import com.example.social_media_app.repository.UserRepo;
import com.example.social_media_app.service.PostService;
import com.example.social_media_app.service.UserService;

@Service
public class PostServiceImpl implements PostService{

	@Autowired
	PostRepo postRepo;
	
	@Autowired
	UserRepo userRepo;
	
	@Autowired
	UserService userService;
	
	@Override
	public Post createPost(Post post, Long userId) throws UserException {
		
		User user = userService.findUserById(userId);
		
		Post newPost =new Post();
		newPost.setCaption(post.getCaption());
		newPost.setImage(post.getImage());
		newPost.setVideo(post.getVideo());
		newPost.setCreatedAt(LocalDateTime.now());
		newPost.setUser(user);
		
		
		return postRepo.save(newPost);
	}

	@Override
	public String deletePost(Long postId, Long userId) throws PostException, UserException {
		User user = userService.findUserById(userId);
		Post post = findPostById(postId);
		
		if(post.getUser().getId() != user.getId()) {
			throw new PostException("You can't delete someone else post");
		}
		postRepo.delete(post);
		
		return "Post deleted Successfully";
	}

	@Override
	public List<Post> findPostByUserId(Long userId) {
		
		return postRepo.findPostByUserId(userId);
	}

	@Override
	public Post findPostById(Long postId) throws PostException {
		return postRepo.findById(postId)
				.orElseThrow(() -> new PostException("Post does not exists"));
	}

	@Override
	public List<Post> findAllPost() {

		return postRepo.findAll();
	}

	@Override
	public Post savedPost(Long userId, Long postId) throws PostException, UserException {
		User user = userService.findUserById(userId);
		Post post = findPostById(postId);
		
		if(user.getSavedPost().contains(post)) {
			user.getSavedPost().remove(post);
		}else {
			user.getSavedPost().add(post);
		}
		userRepo.save(user);
		return post; 
	}

	@Override
	public Post LikePost(Long postId, Long userId) throws PostException, UserException {

		User user = userService.findUserById(userId);
		Post post = findPostById(postId);
		
		if(post.getLiked().contains(user)) {
			post.getLiked().remove(user);
		}else {
			post.getLiked().add(user);	
		}		
		return postRepo.save(post);
	}

}
