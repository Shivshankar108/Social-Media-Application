package com.example.social_media_app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.social_media_app.models.Chat;
import com.example.social_media_app.models.User;

public interface ChatRepo extends JpaRepository<Chat, Long>{
	
	public List<Chat> findByUsersId(Long userId);
	
	@Query("select c from Chat c Where :user Member of c.users and :reqUser Member of c.users")
	public Chat findChatByUsersId(@Param("user") User user, @Param("reqUser") User reqUser);

}
