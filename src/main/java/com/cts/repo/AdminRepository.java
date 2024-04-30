package com.cts.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.model.Admin;
import com.cts.model.Customer;

public interface AdminRepository extends JpaRepository<Admin,Integer> {
	Admin findByEmail(String email);
}
