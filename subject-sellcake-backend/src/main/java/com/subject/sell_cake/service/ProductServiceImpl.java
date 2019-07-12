package com.subject.sell_cake.service;

import com.subject.sell_cake.api.admin.request.PagingRequestModel;
import com.subject.sell_cake.model.entity.Product;
import com.subject.sell_cake.repository.ProductRepository;
import com.subject.sell_cake.repository.specification.ProductClientSpecification;
import com.subject.sell_cake.repository.specification.ProductSpecification;
import com.subject.sell_cake.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Page<Product> pagingBoxProduct(PagingRequestModel pagingRequestModel,String lang) {
        ProductSpecification dfs = new ProductSpecification(pagingRequestModel.getSearchKey(),pagingRequestModel.getSortCase(),pagingRequestModel.isAscSort(),lang);
        PageRequest pageRequest = new PageRequest((pagingRequestModel.getPageNumber() - 1),pagingRequestModel.getPageSize());
        return productRepository.findAll(dfs,pageRequest);
    }

    @Override
    public Page<Product> pagingBoxProductClient(PagingRequestModel pagingRequestModel, String lang) {
        ProductClientSpecification dfs = new ProductClientSpecification(pagingRequestModel.getSearchKey(),pagingRequestModel.getSortCase(),pagingRequestModel.isAscSort(),lang);
        PageRequest pageRequest = new PageRequest((pagingRequestModel.getPageNumber() - 1),pagingRequestModel.getPageSize());
        return productRepository.findAll(dfs,pageRequest);
    }

    @Override
    public List<Product> listProduct(int status) {
        return (List<Product>) productRepository.findByStatus(status);
    }

    @Override
    public Product getDetailProduct(String productID) {
        return productRepository.findProductByProductId(productID);
    }

    @Override
    public List<Product> findProductByProductTypeId(String productTypeID) {
        return productRepository.findProductByProductTypeIdAndStatus(productTypeID, Constant.Status.ACTIVE.getValues());
    }

    @Override
    public Product findProductByProductId(String productId) {
        return productRepository.findProductByProductId(productId);
    }


}
