package com.subject.sell_cake.service;

import com.subject.sell_cake.model.entity.Cart;
import com.subject.sell_cake.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CartServiceImpl implements CartService {

    @Autowired
    CartRepository cartRepository;

    // save cart product
    @Override
    public Cart saveCart(Cart cart) {
        return cartRepository.save(cart);
    }

    // find cart by product id
    @Override
    public Cart findCartByProductId(String productId) {
        return cartRepository.findCartByProductId(productId);
    }

    // find cart by customer
    @Override
    public List<Cart> findCartByCustomerId(String customerId) {
        return cartRepository.findCartByCustomerId(customerId);
    }
    //delete all
    @Override
    public void delete(Cart cart) {
        cartRepository.delete(cart);
    }
}
