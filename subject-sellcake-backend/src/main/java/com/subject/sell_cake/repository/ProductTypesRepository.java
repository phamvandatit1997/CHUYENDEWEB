package com.subject.sell_cake.repository;

import com.subject.sell_cake.model.entity.Product;
import com.subject.sell_cake.model.entity.ProductTypes;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.QueryHint;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface ProductTypesRepository extends PagingAndSortingRepository<ProductTypes,String>, JpaSpecificationExecutor<ProductTypes> {
    // find product type by id and status = 1
    public ProductTypes findProductTypesByProductTypeIdAndStatus(String id, int status);
    // find product type name and status = 1
    public ProductTypes findProductTypesByProductTypeNameAndStatus(String productTypeName, int status);
    // find product type by product type id
    public ProductTypes findProductTypesByProductTypeId(String productTypeId);
    // find all product type by status
    @Query(value = "SELECT pt FROM ProductTypes pt WHERE pt.status = :status")
    List<ProductTypes> findByStatus(@Param(value = "status") int status);
}
