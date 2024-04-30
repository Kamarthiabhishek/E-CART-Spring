package com.cts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.model.Customer;
import com.cts.repo.CustomerRepository;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerRepository custRepo;
	
	public List<Customer> findAllCustomers(){
		return custRepo.findAll();
	}
	
	public Customer findCustomerId(int customerId) {
		return custRepo.findById(customerId).get();
	}
	
	public void deleteByCustomerId(int customerId) {
		custRepo.deleteById(customerId);
	}
	
	public void saveCustomer(Customer customer) {
		custRepo.save(customer);
	}
	
	public Customer findEmailId(String email) {
		return custRepo.findByEmail(email);
	}
}
