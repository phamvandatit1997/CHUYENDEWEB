package com.subject.sell_cake.repository;

import com.subject.sell_cake.model.entity.Product;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface ProductRepository extends PagingAndSortingRepository<Product,String>, JpaSpecificationExecutor<Product> {
    // find product by productID
    public Product findProductByProductId(String productID);
    // find product by product type
    public List<Product> findProductByProductTypeIdAndStatus(String productTypeID, int status);
    // find all product by status
    @Query(value = "SELECT p FROM Product p WHERE p.status = :status")
    List<Product> findByStatus(@Param(value = "status") int status);
}