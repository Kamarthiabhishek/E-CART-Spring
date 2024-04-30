package com.cts.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.model.Admin;
import com.cts.repo.AdminRepository;

@Service
public class AdminService {
	
	@Autowired
	private AdminRepository adminRepo;
	
	public void saveAdmin(Admin admin) {
		adminRepo.save(admin);
	}
	
	public Admin findEmailId(String emial) {
		return adminRepo.findByEmail(emial);
	}
}
