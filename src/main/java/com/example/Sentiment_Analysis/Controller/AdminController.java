package com.example.Sentiment_Analysis.Controller;

import java.security.Principal;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
import com.example.Sentiment_Analysis.Service.AdminService;
import com.example.Sentiment_Analysis.Service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
@CrossOrigin("*")
@RequestMapping("/admin/")
public class AdminController {
	
	private static final Logger logger = LogManager.getLogger(AdminController.class);

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private UserService userService;
	
	@Autowired
	private AdminService adminService;

	@GetMapping("/")
	public String Index(Model model, Principal principal) {
		boolean isLoggedIn = principal != null;
		model.addAttribute("isLoggedIn", isLoggedIn);

		if (isLoggedIn) {
			String email = principal.getName();
			User user = userRepo.findByEmail(email);
			model.addAttribute("user", user);
		}
		model.addAttribute("user", adminService.getAllUser());

		return "AdminPanel/index";
	}
	
	@GetMapping("/addUser")
	public String addUser(Model model, Principal principal) {
	    boolean isLoggedIn = principal != null;

	    // Creating a Logger instance for the class
	    Logger logger = Logger.getLogger(getClass().getName());

	    model.addAttribute("isLoggedIn", isLoggedIn);

	    if (isLoggedIn) {
	        String email = principal.getName();
	        User user = userRepo.findByEmail(email);
	        model.addAttribute("users", user);

	        // Logging the number of appointments displayed and the user's name
	        logger.info("Displayed Create user: " + email);
	    } else {
	        // Logging for guest users
	        logger.info("Displayed 0 appointments for guest");
	    }
	    return "AdminPanel/addUser";
	}
	
	
	 @PostMapping("/submits")
	    public String saveUser(@ModelAttribute User user, HttpSession session) {
	        try {
	            User saveUser = adminService.saveUser(user);
	            
	            logger.info("User saved successfully: {}");
	            return "redirect:/admin/";
	        } catch (Exception e) {
	            logger.error("Error saving user: " + user.getEmail(), e);
	            return "redirect:/errorPage";
	        }
	    }

	    @GetMapping("/editUser")
	    public String editUser(Model model, Principal principal) {
	        boolean isLoggedIn = principal != null;
	        model.addAttribute("isLoggedIn", isLoggedIn);
	        if (isLoggedIn) {
	            String email = principal.getName();
	            User user = userRepo.findByEmail(email);
	            model.addAttribute("users", user);
	        }
	        return "AdminPanel/editUser";
	    }

	    @GetMapping("/editUser/{id}")
	    public String editSUser(@PathVariable Long id, Model model) {
	        try {
	            User user = adminService.getUserByID(id);
	            model.addAttribute("user", user);
	            return "AdminPanel/editUser";
	        } catch (Exception e) {
	            logger.error("Error editing user with ID: {}", e);
	            return "redirect:/errorPage";
	        }
	    }

	    @PostMapping("/editUser/{id}")
	    public String updateUser(@PathVariable Long id, @ModelAttribute("user") User user, Model model) {
	        try {
	            User existingUser = adminService.getUserByID(id);
	            existingUser.setId(id);
	            existingUser.setName(user.getName());
	            existingUser.setEmail(user.getEmail());
	            existingUser.setMobile(user.getMobile());
	            String newPassword = user.getPassword();
	            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	            String hashedPassword = encoder.encode(newPassword);
	            existingUser.setPassword(hashedPassword);
	            adminService.updateUser(existingUser);
	            logger.info("User updated successfully: {}");
	            return "redirect:/admin/";
	        } catch (Exception e) {
	            logger.error("Error updating user with ID: {}", e);
	            return "redirect:/errorPage";
	        }
	    }

	    @GetMapping("/deleteUser/{id}")
	    public String deleteUser(@PathVariable Long id) {
	        try {
	            adminService.deleteUser(id);
	            logger.info("User deleted successfully with ID: {}");
	            return "redirect:/admin/";
	        } catch (Exception e) {
	            logger.error("Error deleting user with ID: {}", e);
	            return "redirect:/errorPage";
	        }
	    }
	
	
	
	
	
	
	
	
	
	
	

	@GetMapping("/submits")
	public String Submissions(Model model, Principal principal) {
		boolean isLoggedIn = principal != null;
		model.addAttribute("isLoggedIn", isLoggedIn);

		if (isLoggedIn) {
			String email = principal.getName();
			User user = userRepo.findByEmail(email);
			model.addAttribute("user", user);
		}
		model.addAttribute("submit", userService.getAllSubmiti());
		return "AdminPanel/submissions";
	}

	
	@GetMapping("/editSubmit/{id}")
	public String editSubmit(@PathVariable Long id, Model model) {
		try {
			Submit submit = userService.getSubmitByID(id);
			model.addAttribute("submit", submit);

			return "AdminPanel/editSubmit";
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

			return "redirect:/admin/submits";
		} catch (Exception e) {
			return "redirect:/errorPage";
		}
	}

	
	
	@GetMapping("/deleteSubmit/{id}")
	public String deleteSubmit(@PathVariable Long id) {
		try {
			userService.deleteSubmit(id);

			return "redirect:/admin/submits";
		} catch (Exception e) {
			return "redirect:/errorPage";
		}
	}

}
