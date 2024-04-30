package com.cts.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.model.Customer;
import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
	Customer findByEmail(String email);

}
