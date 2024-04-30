package com.cts.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cts.model.Products;

public interface ProductRepository extends JpaRepository<Products,Integer>{
//
//	@Query("SELECT DISTINCT category FROM products")
//	List<String> findAllCategories();
	
	List<Products> findByPriceBetween(int minPrice, int maxPrice);
	
	List<Products> findByCategory(String category);
}
