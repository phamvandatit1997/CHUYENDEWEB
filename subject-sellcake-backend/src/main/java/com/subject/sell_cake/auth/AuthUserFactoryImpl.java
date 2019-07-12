package com.subject.sell_cake.auth;

import com.subject.sell_cake.model.entity.Customers;
import com.subject.sell_cake.model.entity.User;
import com.subject.sell_cake.util.Constant;
import org.springframework.stereotype.Service;

@Service
public class AuthUserFactoryImpl implements AuthUserFactory{
    @Override
    public AuthUser createAuthUser(User user) {
        return new AuthUser(
            user.getUserId(),
            user.getUserName(),
            user.getEmail(),
            user.getPassword(),
            user.getFirstName(),
            user.getLastName(),
            user.getLang(),
            user.getStatus() == Constant.Status.ACTIVE.getValues(),
            user.getRole()
        );
    }

    @Override
    public AuthUser createAuthAdmin(User admin) {
        return new AuthUser(
          admin.getUserId(),
          admin.getUserName(),
          admin.getEmail(),
          admin.getPassword(),
          admin.getFirstName(),
          admin.getLastName(),
          admin.getLang(),
          admin.getStatus() == Constant.Status.ACTIVE.getValues(),
          admin.getRole()
        );
    }

    @Override
    public AuthUser createAuthCustomer(Customers customers) {
        return new AuthUser(
           customers.getCustomerId(),
           customers.getCustomerName(),
           customers.getEmail(),
           customers.getPassword(),
           customers.getFirstName(),
           customers.getLastName(),
           "vi",
           customers.getStatus() == Constant.Status.ACTIVE.getValues(),
           "customer"
        );
    }
}
