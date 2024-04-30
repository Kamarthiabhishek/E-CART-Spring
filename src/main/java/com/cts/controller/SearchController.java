package com.cts.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cts.model.Products;
import com.cts.repo.ProductRepository;
import com.cts.service.ProductService;

@Controller
public class SearchController {
	
	@Autowired
	private ProductRepository prodRepo;
	
	@GetMapping("/search")
	public String searchCategories(@RequestParam("keyword") String keyword, Model model) {
		List<Products> result = prodRepo.findByCategory(keyword);
		model.addAttribute("searchResults", result);
		return "customer_functions/search_page";
	}
}
