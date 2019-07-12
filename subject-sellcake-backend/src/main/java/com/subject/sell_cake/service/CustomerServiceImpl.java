package com.subject.sell_cake.service;

import com.subject.sell_cake.api.admin.request.CustomerRequest;
import com.subject.sell_cake.api.admin.request.PagingRequestModel;
import com.subject.sell_cake.model.entity.Customers;
import com.subject.sell_cake.repository.CustomerRepository;
import com.subject.sell_cake.repository.specification.CustomerSpecification;
import com.subject.sell_cake.util.CommonUtil;
import com.subject.sell_cake.util.Constant;
import com.subject.sell_cake.util.DateUtil;
import com.subject.sell_cake.util.MD5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.security.NoSuchAlgorithmException;
import java.util.Date;

@Component
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public Customers findCustomerByEmailAndStatus(String email, int status) {
        return customerRepository.findCustomersByEmailAndStatus(email,status);
    }

    @Override
    public Customers saveCustomer(Customers customers) {
        return customerRepository.save(customers);
    }

    @Override
    public Page<Customers> pagingBoxCustomer(PagingRequestModel pagingRequestModel, String lang) {
        CustomerSpecification dfs = new CustomerSpecification(pagingRequestModel.getSearchKey(), pagingRequestModel.getSortCase(),pagingRequestModel.isAscSort(), lang);
        PageRequest pageRequest = new PageRequest((pagingRequestModel.getPageNumber() - 1),pagingRequestModel.getPageSize());
        return customerRepository.findAll(dfs,pageRequest);
    }

    @Override
    public Customers findCustomerByCustomerId(String customerId) {
        return customerRepository.findCustomersByCustomerId(customerId);
    }

    @Override
    public Customers createCustomer(CustomerRequest customerRequest) {
        Date currentDate = new Date();
        Customers customers = null;
        try {
            customers = new Customers();
            customers.setFirstName(customerRequest.getFirstName());
            customers.setBirthday(customerRequest.getBirthday());
            customers.setLastName(customerRequest.getLastName());
            customers.setCustomerName(customerRequest.getFirstName() + " " + customerRequest.getLastName());
            customers.setGender(customerRequest.getGender());
            customers.setEmail(customerRequest.getEmail());
            customers.setPassword(MD5Hash.MD5Encrypt(customerRequest.getPassword()));
            customers.setAddress(customerRequest.getWard()
                    + " " + customerRequest.getDistrict()
                    + " " + customerRequest.getCity());
            customers.setPhoneNumber(customerRequest.getPhoneNumber());
            customers.setCreateDate(DateUtil.convertToUTC(currentDate));
            customers.setLastActivity(DateUtil.convertToUTC(currentDate));
            customers.setSalt(CommonUtil.generateSalt());
            customers.setStatus(Constant.Status.ACTIVE.getValues());
        } catch (
                NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return customers;
    }
}
