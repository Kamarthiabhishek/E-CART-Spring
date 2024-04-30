package com.cts.controller;

import java.util.List;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cts.model.Admin;
import com.cts.model.Customer;
import com.cts.model.Orders;
import com.cts.repo.OrdersRepository;
import com.cts.service.CustomerService;
import com.cts.service.ProductService;

import jakarta.validation.Valid;

@org.springframework.stereotype.Controller
public class CustomerController {

	@Autowired
	private CustomerService custService;

	@Autowired
	private ProductService prodService;

	@Autowired
	private OrdersRepository orderRepo;

	@GetMapping("/customer")
	public String login(Model model) {
		model.addAttribute("customer", new Customer());
		return "customer_functions/customer_login";
	}

	@GetMapping("/new/customer")
	public String registerForm(Model model) {
		model.addAttribute("customer", new Customer());
		return "customer_functions/customer_register";
	}

	
	@PostMapping("/register/customer")
	public String adminregister(@Valid Customer customer,BindingResult bindingResult, Model model) {
		
		try {
			boolean sucess = NewUser(customer);
			String res="";
			if(bindingResult.hasErrors()) {
				res="customer_functions/customer_register";
			}else if(sucess) {
				Random r = new Random();
				int num = r.nextInt(9000)+1000;
				customer.setCustomer_id(num);
				custService.saveCustomer(customer);
				res = "redirect:/customer";
			}else {
				res = "customer_functions/customer_register";
			}
			return res;
		}catch (DataIntegrityViolationException e) {
			// TODO: handle exception
			model.addAttribute("errorMsg","Email Already Exists");
			return "customer_functions/customer_register";
		}
		
	}
	
	@PostMapping("/dashboard")
	public String Listprods(Model model) {
		// TODO: process POST request
		model.addAttribute("prod", prodService.findAllProducts());
		return "customer_functions/customer_dashboard";
	}

	@GetMapping("/dashboard")
	public String showDashBoard(@RequestParam("id") int customerId, Model model) {
		model.addAttribute("customerId", customerId);
		model.addAttribute("prod", prodService.findAllProducts());
		return "customer_functions/customer_dashboard";
	}

	@PostMapping("/customer/login")
	public String customerLogin(@ModelAttribute Customer cust, BindingResult bindingResult, Model model,
			RedirectAttributes attributes) {
		// TODO: process POST request
		Customer existingUser = custService.findEmailId(cust.getEmail());
		String res = "";
		if (bindingResult.hasErrors()) {
			System.out.println(bindingResult);
			res = "customer_functions/customer_login";
		} else if (existingUser != null && existingUser.getPassword().equals(cust.getPassword())) {
			// Add the customerId to the model
			int id = existingUser.getCustomer_id();
			System.out.println(id);
			String name = existingUser.getFirst_name();
			System.out.println(name);
			model.addAttribute("id", id);
			res = "redirect:/dashboard?id=" + id;
		} else {
			res = "redirect:/customer";
			attributes.addFlashAttribute("error", "Invalid Email or password");
		}
		return res;
	}

	@GetMapping("/customerorders")
	public String displayOrdersForCustomer(@RequestParam("customerId") int customerId, Model model) {
		List<Orders> customerOrders = orderRepo.findByCustomerCustomerId(customerId);
		model.addAttribute("orders", customerOrders);
		model.addAttribute("customerId", customerId);
		return "customer_functions/customer_orders";
	}

	public boolean NewUser(Customer customer) {
		Customer user = custService.findEmailId(customer.getEmail());
		if (user == null || user.getEmail().equals(customer.getEmail())) {
			return true;
		} else {
			return false;
		}
	}
}
