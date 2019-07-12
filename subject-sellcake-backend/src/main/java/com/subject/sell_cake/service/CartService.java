package com.subject.sell_cake.service;

import com.subject.sell_cake.model.entity.Cart;

import java.util.List;

public interface CartService {
    // save cart product
    public Cart saveCart(Cart cart);
    // find cart by product id
    public Cart findCartByProductId(String productId);
    // find cart by customer id
    public List<Cart> findCartByCustomerId(String customerId);
    // delete cart
    public void delete(Cart cart);
}
