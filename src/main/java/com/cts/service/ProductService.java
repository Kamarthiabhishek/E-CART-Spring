package com.cts.service;

import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.model.Products;
import com.cts.repo.ProductRepository;

@Service
public class ProductService {
	private static final Logger logger = LoggerFactory.getLogger(ProductService.class);
	@Autowired
	private ProductRepository prodRepo;
	
	public List<Products> findAllProducts(){
		return prodRepo.findAll();
	}
}
