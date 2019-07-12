package com.subject.sell_cake.service;

import com.subject.sell_cake.api.admin.request.CustomerRequest;
import com.subject.sell_cake.api.admin.request.PagingRequestModel;
import com.subject.sell_cake.model.entity.Customers;
import org.springframework.data.domain.Page;

public interface CustomerService {
    // find customer by customerId and status
    public Customers findCustomerByEmailAndStatus(String email, int status);
    // create customer
    public Customers createCustomer(CustomerRequest customerRequest);
    // save customer
    public Customers saveCustomer(Customers customers);
    // paging sort search
    public Page<Customers> pagingBoxCustomer(PagingRequestModel pagingRequestModel, String lang);
    // find one customer by customerId and status
    public Customers findCustomerByCustomerId(String customerId);
}
