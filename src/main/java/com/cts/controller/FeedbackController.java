package com.cts.controller;

import java.util.List;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cts.model.Customer;
import com.cts.model.Feedbacks;
import com.cts.repo.CustomerRepository;
import com.cts.repo.FeedbackRepository;

@Controller
public class FeedbackController {
	
	@Autowired
	private FeedbackRepository feedRepo;
	
	@Autowired
	private CustomerRepository custRepo;
	@GetMapping("/addfeedback")
	public String showFeedbackForm(@RequestParam("customerId") int customerId, Model model) {
		model.addAttribute("feedbacks", new Feedbacks());
		model.addAttribute("customerId", customerId);
		return "customer_functions/customer_feedback";
	}

	@PostMapping("/submitfeedback")
	public String submitFeedback(@RequestParam("name") String name, @RequestParam("email") String email,
			@RequestParam("feeback") String feedback, @RequestParam("customerId") int customerId, Feedbacks feedbacks) {
		Customer customer = custRepo.findById(customerId).orElse(null);
		Random r = new Random();
		int num = r.nextInt(9000)+1000;
		feedbacks.setFeedbackId(num);
		feedbacks.setCustomer(customer);
		feedRepo.save(feedbacks);
		return "redirect:/dashboard?id="+customerId;

	}
	
	
	@GetMapping("/feedbacks")
	public String ShowFeedbacks(Model model) {
		List<Feedbacks> feedbacks = feedRepo.findAll();
		model.addAttribute("feedbacks", feedbacks);
		return "admin_functions/feedbacks";
	}
	
}
