package com.example.Sentiment_Analysis.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.Sentiment_Analysis.Model.User;


public interface UserRepo extends JpaRepository<User, Long>{
	
	public User findByEmail(String email);

}
