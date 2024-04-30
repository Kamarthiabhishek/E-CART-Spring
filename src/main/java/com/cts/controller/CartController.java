package com.cts.controller;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cts.model.Cart;
import com.cts.model.Customer;
import com.cts.model.Products;
import com.cts.repo.CartRepository;
import com.cts.repo.CustomerRepository;
import com.cts.repo.ProductRepository;

@Controller
public class CartController {

	@Autowired
	private ProductRepository prodRepo;

	@Autowired
	private CartRepository cartRepo;

	@Autowired
	private CustomerRepository custRepo;

	@GetMapping("/cart")
	public String viewCart(Model model, @RequestParam("customerId") int customerId) {
		List<Cart> cartItems = cartRepo.findByCustomerCustomerId(customerId);
		model.addAttribute("cartItems", cartItems);

		int total = cartItems.stream().mapToInt(Cart::getTotal_cost).sum();
		model.addAttribute("bill_amount", total);
		model.addAttribute("customerId", customerId);
		return "customer_functions/customer_cart";
	}

	@GetMapping("/add-to-cart/{productId}")
	public String addToCartForm(@PathVariable("productId") int productId, @RequestParam("customerId") int customerId,
			Model model) {
		Products product = prodRepo.findById(productId).orElse(null);
		Customer customer = custRepo.findById(customerId).orElse(null);

		System.out.println(customer.getCustomer_id());
		if (product != null) {
			model.addAttribute("product", product);
			model.addAttribute("cartItem", new Cart());
			model.addAttribute("customerId", customerId);
			return "customer_functions/add_cart";
		}
		return "redirect:/cart";
	}

	@PostMapping("/add-to-cart")
	public String addToCart(@ModelAttribute Cart cartItem, @RequestParam("productId") int productId,
			@RequestParam("price") int price, @RequestParam("quantity") int qty,
			@RequestParam("customerId") int customerId, Model model) {
		Random r = new Random();
		int num = r.nextInt(9000) + 1000;
		cartItem.setCart_id(num);

		Customer customer = custRepo.findById(customerId).orElse(null);
		cartItem.setCustomer(customer);
		// Get the product from the database
		Products product = prodRepo.findById(productId).orElse(null);

		try {
			if (product != null) {
				if (qty > product.getQuantity()) {
					model.addAttribute("qtyError", "Entered quantity exceeds available quantity");
					model.addAttribute("product", product);
					model.addAttribute("customerId", customerId);
					return "customer_functions/add_cart";
				}
			}
		}catch (InputMismatchException e) {
			// TODO: handle exception
			model.addAttribute("qtyError", "Entered quantity exceeds available quantity");
		}
		

		cartItem.setProductId(productId);
		cartItem.setProduct_name(product.getProductName());
		cartItem.setCategory(product.getCategory());
		int total_cost = cartItem.getQuantity() * cartItem.getPrice();
		cartItem.setTotal_cost(total_cost);
		cartRepo.save(cartItem);
		int custId = customer.getCustomer_id();

		System.out.println(custId);

		product.setQuantity(product.getQuantity() - qty);
		prodRepo.save(product);

		model.addAttribute("customerId", custId);
		return "redirect:/cart?customerId=" + custId;
	}

//	@GetMapping("/add-to-cart")
//	public String addtocart() {
//		return "redirect;/";
//	}

	@GetMapping("/cart/delete/{cart_id}")
	public String deleteCartItem(@PathVariable("cart_id") int cart_id, @RequestParam("customerId") int customerId,
			Model model) {

		cartRepo.deleteById(cart_id);
		model.addAttribute("cart", new Cart());
		model.addAttribute("customerId", customerId);
		return "customer_functions/delete_success";
	}

	@PostMapping("cart/delete/success")
	public String ItemDeleteSuccess(Products product ,Cart cart, Model model, @RequestParam("productId") int productId,
			@RequestParam("quantity") int qty) {
		// TODO: process POST request
		int num = cart.getCart_id();
		model.addAttribute("CartId", num);
		System.out.println(qty);
		System.out.println(product.getQuantity());
		product.setQuantity(product.getQuantity() + qty);
		prodRepo.save(product);
		return "customer_functions/delete_success";
	}
}
