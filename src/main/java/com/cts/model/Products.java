package com.cts.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Products {
	
	@Id
	private int product_id;
	
	@NotBlank(message="Product name cannot be null")
//	@Pattern(regexp="^[a-zA-Z]+$", message="Product Name should contain only alphabets")
	private String productName;
	
	@NotBlank(message="Product Category cannot be null")
//	@Pattern(regexp="^[a-zA-Z]+$", message="Product Quantity should contain only alphabets")
	private String category;
//	@NotNull(message="quantity cannot be empty")
//	@Positive(message="quantity must be positive")
	private int quantity;
//	@NotNull(message="Price cannot be empty")
//	@Positive(message="Price must be positive")
	private int price;
	
	public int getProduct_id() {
		return product_id;
	}
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	
//	public byte[] getImage() {
//		return image;
//	}
//	public void setImage(byte[] image) {
//		this.image = image;
//	}
//	@Override
//	public String toString() {
//		return "Products [product_id=" + product_id + ", category=" + category + ", product_name=" + product_name
//				+ ", quantity=" + quantity + ", price=" + price + ", date=" + date + "]";
//	}
	
	
}
