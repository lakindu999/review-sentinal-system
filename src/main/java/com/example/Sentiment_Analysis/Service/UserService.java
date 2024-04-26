package com.example.Sentiment_Analysis.Service;

import java.util.List;

import com.example.Sentiment_Analysis.Model.Submit;
import com.example.Sentiment_Analysis.Model.User;

public interface UserService {

	public User saveUser(User user);

	List<Submit> getAllSubmiti();

	public Submit submits(Submit submit);

	Submit getSubmitByID(Long id);

	Submit updateSubmit(Submit user);

	void deleteSubmit(Long id);

}
