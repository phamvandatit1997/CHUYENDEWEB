package com.subject.sell_cake.service;

import com.subject.sell_cake.auth.AuthUser;
import com.subject.sell_cake.auth.AuthUserFactory;
import com.subject.sell_cake.model.entity.Customers;
import com.subject.sell_cake.model.entity.Session;
import com.subject.sell_cake.model.entity.User;
import com.subject.sell_cake.repository.UserSessionRepository;
import com.subject.sell_cake.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class AuthServiceImpl extends AbstractBaseService implements AuthService{

    @Autowired
    UserSessionRepository userSessionRepository;

    @Autowired
    AuthUserFactory authUserFactory;

    @Override
    public Session createUserToken(User user) {
        Date currentDate = new Date();
        Session userSession = new Session();
        userSession.setAccountLoginId(user.getUserId());
        userSession.setLoginDate(DateUtil.convertToUTC(currentDate));
        AuthUser authUser = authUserFactory.createAuthUser(user);
        userSession.setSessionData(gson.toJson(authUser));
        userSessionRepository.save(userSession);
        return userSession;
    }

    @Override
    public Session createCustomerToken(Customers customers) {
        Date currentDate = new Date();
        Session customerSession = new Session();
        customerSession.setAccountLoginId(customers.getCustomerId());
        customerSession.setLoginDate(DateUtil.convertToUTC(currentDate));
        AuthUser authCustomer = authUserFactory.createAuthCustomer(customers);
        customerSession.setSessionData(gson.toJson(authCustomer));
        userSessionRepository.save(customerSession);
        return customerSession;
    }


    @Override
    public Session getUserTokenById(String token) {
        return userSessionRepository.findSessionByTokenId(token);
    }

    @Override
    public void deleteUserToken(Session userToken) {
        userSessionRepository.delete(userToken);
    }
}
