package com.cts.repo;

import com.cts.model.*;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cts.model.Customer;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {
	List<Cart> findByCustomerCustomerId(int customer_id);
}
