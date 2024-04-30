package com.cts.service.impl;
import com.cts.model.Cart;
import com.cts.repo.CartRepository;
import com.cts.service.CartService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    public void addToCart(Cart cart) {
        cartRepository.save(cart);
    }
    public List<Cart> getAllCartItems() {
        return cartRepository.findAll();
    }
}
