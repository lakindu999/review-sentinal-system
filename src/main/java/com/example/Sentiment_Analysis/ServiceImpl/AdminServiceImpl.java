package com.example.Sentiment_Analysis.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.Sentiment_Analysis.Model.User;
import com.example.Sentiment_Analysis.Repository.SubmitRepo;
import com.example.Sentiment_Analysis.Repository.UserRepo;
import com.example.Sentiment_Analysis.Service.AdminService;
@Service
public class AdminServiceImpl implements AdminService {
	
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
	public List<User> getAllUser() {
		return userRepo.findAll();
	}

	@Override
	public User getUserByID(Long id) {
		return userRepo.findById(id).get();
	}

	@Override
	public User updateUser(User user) {
		return userRepo.save(user);
	}

	@Override
	public void deleteUser(Long id) {
		userRepo.deleteById(id);
		
	}

}
