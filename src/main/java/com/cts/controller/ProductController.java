package com.cts.controller;


import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.cts.model.Products;
import com.cts.repo.ProductRepository;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class ProductController {
	
	@Autowired
	private ProductRepository productRepo;
	
	@GetMapping("/products")
	public String homeprod(Model model) {
		model.addAttribute("prod", productRepo.findAll());
		//model.addAttribute("categories", productRepo.findAllCategories());
		return "admin_functions/admin_dashboard";
	}
	
	
	@GetMapping("/new/product")
	public String showaddpage(Model model) {
		Products product = new Products();
		model.addAttribute("product",product);
		return "admin_functions/add_products";
	}
	
	@GetMapping("/producthome")
	public String method(Model model) {
		model.addAttribute("prod", productRepo.findAll());
		return "redirect:/products";
	}
	
	
	@PostMapping("/product")
	public String add(@Valid Products p, Model model, BindingResult bindingResult) {
		//TODO: process POST request
		if(bindingResult.hasErrors()) {
			return "admin_functions/add_products";
		}else {
			Random r = new Random();
			int num = r.nextInt(9000)+1000;
			p.setProduct_id(num);
			productRepo.save(p);
			model.addAttribute("ProductId", num);
			return "product_success";
		}
		
	}
	
	@GetMapping("/product/{product_id}")
	public String prodUpdateForm(@PathVariable("product_id") int product_id, Model model) {
		System.out.println("1.");
		Products p = productRepo.findById(product_id).get();
		model.addAttribute("prod",p);
		return "admin_functions/update_product";
	}
	
	@PostMapping("product/update")
	public String updateProduct(Products p) {
		//TODO: process POST request
		productRepo.save(p);
		return "redirect:/products";
	}   
	
	@GetMapping("/product/delete/{product_id}")
	public String deleteProduct(@PathVariable("product_id")int product_id, Model model) {
		productRepo.deleteById(product_id);
		Products p = new Products();
		model.addAttribute("prod",p);
		return "admin_functions/delete_success";
	}
	
	@GetMapping("/filter")
	public String filter() {
		return "admin_functions/filter_products";
	}
	
	@PostMapping("/products/filter")
	public String filterProductsByPriceRange(@RequestParam("minPrice") int minPrice,
	                                          @RequestParam("maxPrice") int maxPrice,
	                                          Model model) {
	    List<Products> products = productRepo.findByPriceBetween(minPrice, maxPrice);
	    model.addAttribute("prod", products);
	    return "admin_functions/filter_products";
	}

	
	@PostMapping("/delete/success")
	public String deleteSuccess(Products p , Model model) {
		//TODO: process POST request
		int num = p.getProduct_id();
		model.addAttribute("ProductId", num);
		return "admin_functions/delete_success";
	}
	
//	@GetMapping("/products/{category}")
//	public String getProductsByCategory(@PathVariable("category") String category, Model model) {
//	    List<Products> products = productRepo.findbyCategory();
//	    model.addAttribute("prod", products);
//	    return "admin_functions/admin_products";
//	}

	
	
}
