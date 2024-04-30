package com.cts.model;

import java.sql.Date;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
public class Admin {

	@Id
	private int Admin_id;

	@NotBlank(message="First name cannot be null")
	@Pattern(regexp="^[a-zA-Z]+$", message="First Name should contain only alphabets")
	@Size(max = 100, message = "Name should not exceed 100 characters")
	private String first_name;
	@NotBlank(message="Last name cannot be null")
	@Pattern(regexp="^[a-zA-Z ]+$", message="Last Name should contain only alphabets")
	private String last_name;
	@NotBlank(message="Gender cannot be null")
	@Pattern(regexp="^[a-zA-Z ]+$", message="Gender should contain only alphabets")
	private String gender;
	@NotBlank(message="Email cannot be null")
	@Email(message = "Invalid Email")
	@Column(unique = true)
	private String email;
	@NotBlank(message="Password cannot be null")
	@Pattern(regexp="^(?=.*[A-Z])(?=.*[@#$%^&+=!0-9]).{8,}$", message="Invalid password")
	private String password;
	@NotBlank(message="Address cannot be null")
	private String address;
	private Date dob;

	public int getAdmin_id() {
		return Admin_id;
	}
	public void setAdmin_id(int admin_id) {
		Admin_id = admin_id;
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
