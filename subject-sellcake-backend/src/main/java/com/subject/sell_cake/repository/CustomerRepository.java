package com.subject.sell_cake.repository;

import com.subject.sell_cake.model.entity.Customers;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface CustomerRepository extends CrudRepository<Customers,String>, JpaSpecificationExecutor<Customers> {
    // find customer email and status
    public Customers findCustomersByEmailAndStatus(String email, int status);
    // find customer ID and status
    public Customers findCustomersByCustomerId(String customerId);

}
