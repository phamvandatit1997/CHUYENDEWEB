package com.subject.sell_cake.repository;

import com.subject.sell_cake.model.entity.Cart;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface CartRepository extends CrudRepository<Cart, String> {
    // find cart by product id
    public Cart findCartByProductId(String productId);
    // find cart by customer id
    public List<Cart> findCartByCustomerId(String customerId);
}
