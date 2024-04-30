package com.cts.controller;

import java.util.Random;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.cts.model.Cart;
import com.cts.model.Customer;
import com.cts.model.Orders;
import com.cts.repo.CartRepository;
import com.cts.repo.CustomerRepository;
import com.cts.repo.OrdersRepository;
import org.springframework.web.bind.annotation.PostMapping;




@Controller
public class Payment_controller {
	
	@Autowired
	private CartRepository cartRepo;
	
	@Autowired
	private OrdersRepository orderRepo;
	
	@Autowired
	private CustomerRepository custRepo;
	
	@GetMapping("/buy/{cart_id}")
	public String showBuyForm(@PathVariable("cart_id") int cart_id,@RequestParam("customerId") int customerId, Model model) {
		Cart cart = cartRepo.findById(cart_id).get();
		if(cart != null) {
			model.addAttribute("cart", cart);
			model.addAttribute("customerId", customerId);
			cartRepo.deleteById(cart_id);
			return "customer_functions/customer_payment";
		}else {
			return "redirect:/error";
		}
		
	}

	@PostMapping("/buy")
	public String placeOrder(Orders order, @RequestParam("customerId") int customerId, @RequestParam("total") int total_cost, Model model) {
		//TODO: process POST request
		Customer customer = custRepo.findById(customerId).orElse(null);
		if(customer != null) {
			order.setCustomer(customer);
			order.setBill_amount(total_cost);
			
			model.addAttribute("customerId", customerId);
		}
		Random r = new Random();
		int num = r.nextInt(9000)+1000;
		order.setOrder_id(num);
		orderRepo.save(order);
		return "customer_functions/payment_success";
	}
	
	
}
