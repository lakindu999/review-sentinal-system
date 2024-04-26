package com.example.Sentiment_Analysis.Controller;

import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.Sentiment_Analysis.Model.User;
import com.example.Sentiment_Analysis.Repository.UserRepo;
import com.example.Sentiment_Analysis.Service.UserService;
import jakarta.servlet.http.HttpSession;

@Controller
@CrossOrigin("*")
public class FrontPageController {
	
	
	@Autowired
	private UserService userService;

	@Autowired
	private UserRepo userRepo;
	
	
	
	@GetMapping("/")
	public String Index(Model model, Principal principal) {
		boolean isLoggedIn = principal != null;

		model.addAttribute("isLoggedIn", isLoggedIn);

		if (isLoggedIn) {
			String email = principal.getName();
			User user = userRepo.findByEmail(email);
			model.addAttribute("user", user);
		}
		return "index";
	}
	
	
	@GetMapping("/service")
	public String service(Model model, Principal principal) {
		boolean isLoggedIn = principal != null;

		model.addAttribute("isLoggedIn", isLoggedIn);

		if (isLoggedIn) {
			String email = principal.getName();
			User user = userRepo.findByEmail(email);
			model.addAttribute("user", user);
		}
		return "service";
	}
	
	@GetMapping("/about")
	public String about(Model model, Principal principal) {
		boolean isLoggedIn = principal != null;

		model.addAttribute("isLoggedIn", isLoggedIn);

		if (isLoggedIn) {
			String email = principal.getName();
			User user = userRepo.findByEmail(email);
			model.addAttribute("user", user);
		}
		return "about";
	}
	
	
	@GetMapping("/contact")
	public String contact(Model model, Principal principal) {
		boolean isLoggedIn = principal != null;

		model.addAttribute("isLoggedIn", isLoggedIn);

		if (isLoggedIn) {
			String email = principal.getName();
			User user = userRepo.findByEmail(email);
			model.addAttribute("user", user);
		}
		return "contact";
	}
	
	@GetMapping("/signin")
	public String singIn(Model model) {
		return "signIn";
	}

	@GetMapping("/signup")
	public String singUp(Model model, Principal principal) {

		return "signUp";
	}
	
	@PostMapping("/saveUser")
	public String saveUser(@ModelAttribute User user, HttpSession session) {
		try {
			User existingUser = userRepo.findByEmail(user.getEmail());

			if (existingUser != null) {
				session.setAttribute("msgError", "Email address already exists. Please use a different email.");
				session.removeAttribute("msg");
				return "redirect:/signup";
			} else {
				User savedUser = userService.saveUser(user);

				if (savedUser != null) {
					session.setAttribute("msg", "Registration successful. Please sign in.");
					session.removeAttribute("msgError");
					return "redirect:/signup";
				} else {
					session.setAttribute("msgError", "Something went wrong on the server.");
					session.removeAttribute("msg");
					return "redirect:/signup";
				}
			}
		} catch (Exception e) {
			return "redirect:/errorPage";
		}
	}
}
