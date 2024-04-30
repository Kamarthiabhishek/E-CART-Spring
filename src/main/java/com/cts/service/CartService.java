package com.cts.service;

import java.util.List;


import com.cts.model.Cart;

public interface CartService {
	
    void addToCart(Cart cart);
    List<Cart> getAllCartItems();
}
