package com.cts.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.model.Orders;

public interface OrdersRepository extends JpaRepository<Orders, Integer> {
	List<Orders> findByCustomerCustomerId(int customer_id);
}
