package com.subject.sell_cake.service;

import com.subject.sell_cake.api.admin.request.PagingRequestModel;
import com.subject.sell_cake.model.entity.ProductTypes;
import com.subject.sell_cake.repository.ProductTypesRepository;
import com.subject.sell_cake.repository.specification.ProductTypesSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class ProductTypeServiceImpl implements ProductTypeService{

    @Autowired
    ProductTypesRepository productTypesRepository;

    @Autowired
    FileUploadService fileUploadService;

    @Override
    public ProductTypes saveProductType(ProductTypes productTypes) {
        return productTypesRepository.save(productTypes);
    }

    @Override
    public Page<ProductTypes> pagingBoxProductType(PagingRequestModel pagingRequestModel, String lang) {
        ProductTypesSpecification dfs = new ProductTypesSpecification(pagingRequestModel.getSearchKey(),pagingRequestModel.getSortCase(),pagingRequestModel.isAscSort(),lang);
        PageRequest pageRequest = new PageRequest((pagingRequestModel.getPageNumber() - 1),pagingRequestModel.getPageSize());
        return productTypesRepository.findAll(dfs,pageRequest);
    }

    @Override
    public ProductTypes findProductTypeIdAndStatus(String id, int status) {
        return productTypesRepository.findProductTypesByProductTypeIdAndStatus(id,status);
    }

    @Override
    public ProductTypes findProductTypeByProductTypeNameAndStatus(String productTypeName, int status) {
        return productTypesRepository.findProductTypesByProductTypeNameAndStatus(productTypeName,status);
    }

    @Override
    public ProductTypes findProductTypeByProductTypeId(String productTypeId) {
        return productTypesRepository.findProductTypesByProductTypeId(productTypeId);
    }

    @Override
    public List<ProductTypes> getListProductType(int status) {
        return productTypesRepository.findByStatus(status);
    }
}
