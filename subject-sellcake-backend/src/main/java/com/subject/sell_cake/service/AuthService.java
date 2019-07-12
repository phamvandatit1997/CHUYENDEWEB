package com.subject.sell_cake.service;

import com.subject.sell_cake.model.entity.Customers;
import com.subject.sell_cake.model.entity.Session;
import com.subject.sell_cake.model.entity.User;

public interface AuthService {
    // create session user
    public Session createUserToken(User user);
    // create session customer
    public Session createCustomerToken(Customers customers);
    // get token by user and admin
    public Session getUserTokenById(String token);
    // delete token user and admin
    public void deleteUserToken(Session userToken);
}
