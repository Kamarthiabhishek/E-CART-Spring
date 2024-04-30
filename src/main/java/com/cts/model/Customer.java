package com.cts.model;

import java.sql.Date;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Entity
public class Customer {

	@Id
	private int customerId;

	@NotBlank(message = "First name is required")
	@Pattern(regexp="^[a-zA-Z]+$", message = "First name should be only alphabets")
	private String first_name;
	@NotBlank(message = "Last is required")
	@Pattern(regexp="^[a-zA-Z]+$", message = "Last name should be only alphabets")
	private String last_name;
	@NotBlank(message = "Gender is required")
	private String gender;
	@NotBlank(message="Email cannot be null")
	@Email(message = "Invalid Email")
	@Column(unique = true)
	private String email;
	@NotBlank(message = "Password is required")
	private String password;
	@NotBlank(message = "Address is required")
	private String address;
	private Date dob;

	public int getCustomer_id() {
		return customerId;
	}

	public void setCustomer_id(int customer_id) {
		this.customerId = customer_id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

}
