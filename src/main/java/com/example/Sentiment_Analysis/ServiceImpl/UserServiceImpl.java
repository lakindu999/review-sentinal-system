package com.example.Sentiment_Analysis.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.Sentiment_Analysis.Model.Submit;
import com.example.Sentiment_Analysis.Model.User;
import com.example.Sentiment_Analysis.Repository.SubmitRepo;
import com.example.Sentiment_Analysis.Repository.UserRepo;
import com.example.Sentiment_Analysis.Service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private SubmitRepo submitRepo;

	@Override
	public User saveUser(User user) {
		String password = passwordEncoder.encode(user.getPassword());
		user.setPassword(password);
		user.setRole("ROLE_USER");
		User newUser= userRepo.save(user);
		return newUser;
	}

	@Override
	public List<Submit> getAllSubmiti() {
		return submitRepo.findAll();
	}

	@Override
	public Submit submits(Submit submit) {
		return submitRepo.save(submit);
	}

	@Override
	public Submit getSubmitByID(Long id) {
		return submitRepo.findById(id).get();
	}

	@Override
	public Submit updateSubmit(Submit user) {
		return submitRepo.save(user);
	}

	@Override
	public void deleteSubmit(Long id) {
		submitRepo.deleteById(id);
	}

	


	
	

}
