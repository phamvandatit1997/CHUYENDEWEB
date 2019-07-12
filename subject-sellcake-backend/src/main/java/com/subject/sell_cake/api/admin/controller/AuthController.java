package com.subject.sell_cake.api.admin.controller;

import com.subject.sell_cake.api.AbstractBasedAPI;
import com.subject.sell_cake.api.admin.request.AuthRequest;
import com.subject.sell_cake.exception.ApplicationException;
import com.subject.sell_cake.model.RestAPIResponse;
import com.subject.sell_cake.model.entity.Customers;
import com.subject.sell_cake.model.entity.Session;
import com.subject.sell_cake.model.entity.User;
import com.subject.sell_cake.response.APIStatus;
import com.subject.sell_cake.service.AuthService;
import com.subject.sell_cake.service.CustomerService;
import com.subject.sell_cake.service.UserService;
import com.subject.sell_cake.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = Constant.API)
public class AuthController extends AbstractBasedAPI {

    @Autowired
    AuthService authService;

    @Autowired
    UserService userService;

    @Autowired
    CustomerService customerService;

    // user login
    @RequestMapping(path = Constant.LOGIN_USER_API,method = RequestMethod.POST)
    public ResponseEntity<RestAPIResponse> userLogin(@RequestBody AuthRequest authRequest)
    {
        User user = userService.findUserByEmailAndStatus(authRequest.getEmail(), Constant.Status.ACTIVE.getValues());
        if (user == null)
        {
            throw new ApplicationException(APIStatus.ERR_USER_NOT_FOUND);
        }
        if (!(user.getPassword() + user.getSalt()).equals((authRequest.getPassword() + user.getSalt()))) {
            throw new ApplicationException(APIStatus.ERR_PASSWORD_NOT_MATCH);
        }
        Session session = authService.createUserToken(user);
        return responseUtil.successResponse(session.getTokenId());
    }
    // customer login
    @RequestMapping(path = Constant.LOGIN_CUSTOMER_API, method = RequestMethod.POST)
    public ResponseEntity<RestAPIResponse> customerLogin(@RequestBody AuthRequest authRequest) {
        Customers customers = customerService.findCustomerByEmailAndStatus(authRequest.getEmail(), Constant.Status.ACTIVE.getValues());
        if (customers == null){
            throw new ApplicationException(APIStatus.ERR_CUSTOMER_NOT_FOUND);
        } else {
            if (!(customers.getPassword() + customers.getSalt()).equals((authRequest.getPassword() + customers.getSalt()))) {
                throw new ApplicationException(APIStatus.ERR_PASSWORD_NOT_MATCH);
            } else {
                Session customerSession = authService.createCustomerToken(customers);
                return responseUtil.successResponse(customerSession.getTokenId());
            }
        }
    }
    // user logout
    @RequestMapping(path = Constant.LOGOUT_API,method = RequestMethod.GET)
    public ResponseEntity<RestAPIResponse> userLogout(@RequestHeader(value = Constant.HEADER_TOKEN) String token)
    {
        if (token != null && !"".equals(token)) {
            Session userToken = authService.getUserTokenById(token);
            if (userToken != null) {
                authService.deleteUserToken(userToken);
                return responseUtil.successResponse(APIStatus.OK);
            } else {
                throw new ApplicationException(APIStatus.ERR_SESSION_NOT_FOUND);
            }
        }
        throw new ApplicationException(APIStatus.ERR_BAD_REQUEST);
    }
}
