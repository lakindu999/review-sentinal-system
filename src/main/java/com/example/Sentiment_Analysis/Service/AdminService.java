package com.example.Sentiment_Analysis.Service;

import java.util.List;
import com.example.Sentiment_Analysis.Model.User;

public interface AdminService {

	public User saveUser(User user);

	List<User> getAllUser();

	User getUserByID(Long id);

	User updateUser(User user);
	
	void deleteUser(Long id);
}
