package com.cts.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Feedbacks {
	
	@Id
	private int feedbackId;
	@ManyToOne
	@JoinColumn(name="customerId", referencedColumnName = "customerId")
	private Customer customer;
	
	@NotBlank(message="Feedback cannot be empty")
	private String feeback;
	@Email
	@NotBlank(message="Email cannot be empty")
	private String email;
	@NotBlank(message="Name cannot be empty")
	private String name;

	public int getFeedbackId() {
		return feedbackId;
	}

	public void setFeedbackId(int feedbackId) {
		this.feedbackId = feedbackId;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getFeeback() {
		return feeback;
	}

	public void setFeeback(String feeback) {
		this.feeback = feeback;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}	
}
