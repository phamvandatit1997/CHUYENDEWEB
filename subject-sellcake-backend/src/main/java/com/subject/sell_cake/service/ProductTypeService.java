package com.subject.sell_cake.service;

import com.subject.sell_cake.api.admin.request.PagingRequestModel;
import com.subject.sell_cake.model.entity.ProductTypes;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductTypeService {
    // save prduct types
    public ProductTypes saveProductType(ProductTypes productTypes);
    // paging search sort
    public Page<ProductTypes> pagingBoxProductType(PagingRequestModel pagingRequestModel, String lang);
    // delete product type
    public ProductTypes findProductTypeIdAndStatus(String id, int status);
    // find product type by product type name and status
    public ProductTypes findProductTypeByProductTypeNameAndStatus(String productTypeName, int status);
    // find product type by id
    public ProductTypes findProductTypeByProductTypeId(String productTypeId);
    // get list product type
    public List<ProductTypes> getListProductType(int status);
}
