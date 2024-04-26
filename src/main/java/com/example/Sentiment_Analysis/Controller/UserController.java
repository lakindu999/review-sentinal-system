package com.example.Sentiment_Analysis.Controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.Sentiment_Analysis.Model.Submit;
import com.example.Sentiment_Analysis.Model.User;
import com.example.Sentiment_Analysis.Repository.UserRepo;
import com.example.Sentiment_Analysis.Service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
@CrossOrigin("*")
@RequestMapping("/user/")
public class UserController {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private UserService userService;

	@GetMapping("/submit")
	public String submit(Model model, Principal principal) {
		boolean isLoggedIn = principal != null;

		model.addAttribute("isLoggedIn", isLoggedIn);

		if (isLoggedIn) {
			String email = principal.getName();
			User user = userRepo.findByEmail(email);
			model.addAttribute("users", user);
		}
		model.addAttribute("submit", userService.getAllSubmiti());
		return "/submit";
	}

	@GetMapping("/addSubmit")
	public String addSubmit(Model model, Principal principal) {
		boolean isLoggedIn = principal != null;

		model.addAttribute("isLoggedIn", isLoggedIn);

		if (isLoggedIn) {
			String email = principal.getName();
			User user = userRepo.findByEmail(email);
			model.addAttribute("users", user);
		}
		return "/addSubmit";
	}

	@PostMapping("/submits")
	public String saveSubmit(@ModelAttribute Submit submit, HttpSession session) {
		Submit saveSubmit = userService.submits(submit);
		return "redirect:/user/submit";
	}
	
	@GetMapping("/editSubmit")
	public String editSubmit(Model model, Principal principal) {
		boolean isLoggedIn = principal != null;

		model.addAttribute("isLoggedIn", isLoggedIn);

		if (isLoggedIn) {
			String email = principal.getName();
			User user = userRepo.findByEmail(email);
			model.addAttribute("users", user);
		}
		return "/editSubmit";
	}
	
	
	
	@GetMapping("/editSubmit/{id}")
	public String editSubmit(@PathVariable Long id, Model model) {
		try {
			Submit submit = userService.getSubmitByID(id);
			model.addAttribute("submit", submit);

			return "editSubmit";
		} catch (Exception e) {
			return "redirect:/errorPage";
		}
	}

	@PostMapping("/editSubmit/{id}")
	public String updateSubmit(@PathVariable Long id, @ModelAttribute("submit") Submit submit, Model model) {
		try {
			Submit existingSubmit = userService.getSubmitByID(id);
			existingSubmit.setId(id);
			existingSubmit.setName(submit.getName());
			existingSubmit.setCluster(submit.getCluster());
			existingSubmit.setDate(submit.getDate());
			existingSubmit.setFeedbacks(submit.getFeedbacks());
			existingSubmit.setPositive_feedback(submit.getPositive_feedback());
			existingSubmit.setNegative_feedback(submit.getNegative_feedback());


			userService.updateSubmit(existingSubmit);

			return "redirect:/user/submit";
		} catch (Exception e) {
			return "redirect:/errorPage";
		}
	}

	@GetMapping("/deleteSubmit/{id}")
	public String deleteSubmit(@PathVariable Long id) {
		try {
			userService.deleteSubmit(id);

			return "redirect:/user/submit";
		} catch (Exception e) {
			return "redirect:/errorPage";
		}
	}
	
	
	
	
	

}
