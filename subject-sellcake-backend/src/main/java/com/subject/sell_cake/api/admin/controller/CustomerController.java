package com.subject.sell_cake.api.admin.controller;

import com.subject.sell_cake.api.AbstractBasedAPI;
import com.subject.sell_cake.api.admin.request.CustomerRequest;
import com.subject.sell_cake.api.admin.request.PagingRequestModel;
import com.subject.sell_cake.auth.AuthUser;
import com.subject.sell_cake.exception.ApplicationException;
import com.subject.sell_cake.model.RestAPIResponse;
import com.subject.sell_cake.model.entity.Customers;
import com.subject.sell_cake.response.APIStatus;
import com.subject.sell_cake.service.AuthService;
import com.subject.sell_cake.service.BillsService;
import com.subject.sell_cake.service.CustomerService;
import com.subject.sell_cake.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(path = Constant.CUSTOMER)
public class CustomerController extends AbstractBasedAPI {

    @Autowired
    CustomerService customerService;

    @Autowired
    BillsService billsService;

    @Autowired
    AuthService authService;

    // create customer
    @RequestMapping(path = Constant.CUSTOMER_CREATE, method = RequestMethod.POST)
    public ResponseEntity<RestAPIResponse> createCustomer(@RequestBody CustomerRequest customerRequest) {
        Customers customers = customerService.findCustomerByEmailAndStatus(customerRequest.getEmail(), Constant.Status.ACTIVE.getValues());
        if (customers == null) {
            Customers newCustomer = customerService.createCustomer(customerRequest);
            return responseUtil.successResponse(customerService.saveCustomer(newCustomer));
        } else {
            throw new ApplicationException(APIStatus.ERR_EMAIL_ALREADY_EXISTED);
        }
    }
    // paging search sort ascSort
    @RequestMapping(path = Constant.CUSTOMER_GET_LIST, method = RequestMethod.POST)
    public ResponseEntity<RestAPIResponse> pagingBoxCustomer(HttpServletRequest request, @RequestBody PagingRequestModel pagingRequestModel) {
        AuthUser authUser = getAuthUserFromSession(request);
        if (authUser != null) {
            Page<Customers> customerLists = customerService.pagingBoxCustomer(pagingRequestModel, authUser.getLang());
            return responseUtil.successResponse(customerLists);
        }
        throw new ApplicationException(APIStatus.ERR_USER_NOT_FOUND);
    }
    // find one customer by customerId and status
    @RequestMapping(path = Constant.GET_ONE_CUSTOMER,method = RequestMethod.GET)
    public ResponseEntity<RestAPIResponse> getOneCustomer(HttpServletRequest request, @PathVariable String id) {
        AuthUser authUser = getAuthUserFromSession(request);
        if (authUser != null) {
            Customers customers = customerService.findCustomerByCustomerId(id);
            if (customers != null) {
                return responseUtil.successResponse(customers);
            } else {
                throw new ApplicationException(APIStatus.ERR_CUSTOMER_NOT_FOUND);
            }
        }
        throw new ApplicationException(APIStatus.ERR_USER_NOT_FOUND);
    }
    // change status customer
    @RequestMapping(path = Constant.CHANGE_STATUS, method = RequestMethod.PUT)
    public ResponseEntity<RestAPIResponse> changeStatus(HttpServletRequest request, @Param(value = "customer_id") String customer_id) {
        AuthUser authUser = getAuthUserFromSession(request);
        if (authUser != null) {
            Customers customers = customerService.findCustomerByCustomerId(customer_id);
            if (customers != null) {
                if (customers.getStatus() == Constant.Status.ACTIVE.getValues()) {
                    customers.setStatus(Constant.Status.DELETE.getValues());
                    return responseUtil.successResponse(customerService.saveCustomer(customers));
                } else {
                    customers.setStatus(Constant.Status.ACTIVE.getValues());
                    return responseUtil.successResponse(customerService.saveCustomer(customers));
                }
            }
            throw new ApplicationException(APIStatus.ERR_CUSTOMER_NOT_FOUND);
        }
        throw new ApplicationException(APIStatus.ERR_USER_NOT_FOUND);
    }
    // customer detail in by token
    @RequestMapping(path = Constant.DETAIL_CUSTOMER, method = RequestMethod.GET)
    public ResponseEntity<RestAPIResponse> detailCustomer(HttpServletRequest request) {
        AuthUser authUser = getAuthUserFromSession(request);
        Customers customers = customerService.findCustomerByEmailAndStatus(authUser.getEmail(), Constant.Status.ACTIVE.getValues());
        System.out.println(customers.getCustomerId());
        if (customers == null) {
            throw new ApplicationException(APIStatus.ERR_CUSTOMER_NOT_FOUND);
        } else {
            return responseUtil.successResponse(customers);
        }
    }

    // get bill detail by customer id

}
