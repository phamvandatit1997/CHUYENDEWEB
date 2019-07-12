package com.subject.sell_cake.service;

import com.subject.sell_cake.api.admin.request.PagingRequestModel;
import com.subject.sell_cake.model.entity.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {
    // save product
    public Product saveProduct(Product product);
    // paging sort search
    public Page<Product> pagingBoxProduct(PagingRequestModel pagingRequestModel, String lang);
    public Page<Product> pagingBoxProductClient(PagingRequestModel pagingRequestModel, String lang);
    // list product
    public List<Product> listProduct(int status);
    // detail product
    public Product getDetailProduct(String productID);
    // find product by product type id
    public List<Product> findProductByProductTypeId(String productTypeID);
    // find product by product id
    public Product findProductByProductId(String productId);

}
