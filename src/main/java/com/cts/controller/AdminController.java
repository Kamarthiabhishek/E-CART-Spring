package com.cts.controller;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.cts.model.Admin;
import com.cts.model.Customer;
import com.cts.service.AdminService;
import com.cts.service.CustomerService;
import com.cts.service.ProductService;

import jakarta.validation.Valid;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AdminController {

	@Autowired
	private AdminService adminService;
	
	@Autowired
	private ProductService prodService;
	
	@Autowired
	private CustomerService custService;


	@GetMapping("/admin")
	public String adminLogin(Model model) {
		model.addAttribute("admin", new Admin());
		return "admin_functions/admin_login";
	}

	@GetMapping("/new/admin")
	public String adminregisterForm(Model model) {
		model.addAttribute("admin", new Admin());
		return "admin_functions/admin_register";
	}

	@PostMapping("/register/admin")
	public String adminregister(@Valid Admin admin,BindingResult bindingResult, Model model) {
		
		try {
			boolean sucess = NewUser(admin);
			String res="";
			if(bindingResult.hasErrors()) {
				res="admin_functions/admin_register";
			}else if(sucess) {
				Random r = new Random();
				int num = r.nextInt(9000)+1000;
				admin.setAdmin_id(num);
				adminService.saveAdmin(admin);
				res = "redirect:/admin";
			}else {
				res = "admin_functions/admin_register";
			}
			return res;
		}catch (DataIntegrityViolationException e) {
			// TODO: handle exception
			model.addAttribute("errorMsg","Email Already Exists");
			return "admin_functions/admin_register";
		}
		
	}

	@GetMapping("/adminhome")
	public String customerDisplay(Model model) {
		model.addAttribute("prod", prodService.findAllProducts() );
		return "admin_functions/admin_dashboard";
	}

	@PostMapping("/adminhome")
	public String customersDisplay(Model model) {
		// TODO: process POST request
		model.addAttribute("prod", prodService.findAllProducts());
		return "admin_functions/admin_dashboard";
	}

	@GetMapping("/customers")
	public String displayCustomer(Model model) {
		model.addAttribute("customer", custService.findAllCustomers());
		return "admin_functions/customers";
	}

	@GetMapping("/customer/{customer_id}")
	public String CustomerUpdateForm(@PathVariable("customer_id") int customer_id, Model model) {
		System.out.println("1.");
		Customer c = custService.findCustomerId(customer_id);
		model.addAttribute("customer", c);
		return "admin_functions/update_customer";
	}

	@PostMapping("customer/update")
	public String updateCustomer(Customer cust) {
		// TODO: process POST request
		custService.saveCustomer(cust);
		return "redirect:/customers";
	}

	@GetMapping("/customer/delete/{customer_id}")
	public String deleteCustomer(@PathVariable("customer_id") int customer_id, Model model) {
		custService.deleteByCustomerId(customer_id);
		Customer cust = new Customer();
		model.addAttribute("customer", cust);
		return "admin_functions/delete_success";
	}

	@PostMapping("/admin/login")
	public String customerLogin(@ModelAttribute Admin admin, BindingResult bindingResult, Model model,
			RedirectAttributes attributes) {
		// TODO: process POST request
		Admin existingUser = adminService.findEmailId(admin.getEmail());
		String res = "";
		if (bindingResult.hasErrors()) {
			System.out.println(bindingResult);
			res = "admin_functions/admin_login";
		} else if (existingUser != null && existingUser.getPassword().equals(admin.getPassword())) {
			// Add the customerId to the model
			int id = existingUser.getAdmin_id();
			System.out.println(id);
			String name = existingUser.getFirst_name();
			System.out.println(name);
			model.addAttribute("id", id);
			res = "redirect:/adminhome";
		} else {
			res = "redirect:/admin";
			attributes.addFlashAttribute("error", "Invalid Email or password");
		}
		return res;
	}


	public boolean login(Admin admin) {
		Admin existingUser = adminService.findEmailId(admin.getEmail());
		if (existingUser != null && existingUser.getPassword().equals(admin.getPassword())
				&& existingUser.getEmail() != admin.getEmail()) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean NewUser(Admin admin) {
		Admin user = adminService.findEmailId(admin.getEmail());
		if (user == null || user.getEmail().equals(admin.getEmail())) {
			return true;
		} else {
			return false;
		}
	}
}
