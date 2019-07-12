package com.subject.sell_cake.auth;

import com.subject.sell_cake.model.entity.Customers;
import com.subject.sell_cake.model.entity.User;

public interface AuthUserFactory {
    // create authen user
    public AuthUser createAuthUser(User user);
    // create authen admin
    public AuthUser createAuthAdmin(User admin);
    // create authen customer
    public AuthUser createAuthCustomer(Customers customers);
}
