package com.subject.sell_cake.api.admin.controller;

import com.subject.sell_cake.api.AbstractBasedAPI;
import com.subject.sell_cake.api.admin.request.PagingRequestModel;
import com.subject.sell_cake.api.admin.request.ProductTypeRequest;
import com.subject.sell_cake.auth.AuthUser;
import com.subject.sell_cake.exception.ApplicationException;
import com.subject.sell_cake.model.RestAPIResponse;
import com.subject.sell_cake.model.entity.ProductTypes;
import com.subject.sell_cake.response.APIStatus;
import com.subject.sell_cake.service.FileUploadService;
import com.subject.sell_cake.service.ProductTypeService;
import com.subject.sell_cake.util.CommonUtil;
import com.subject.sell_cake.util.Constant;
import com.subject.sell_cake.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(path = Constant.PRODUCT_TYPES)
public class ProductTypeController extends AbstractBasedAPI {

    @Autowired
    ProductTypeService productTypeService;

    @Autowired
    FileUploadService fileUploadService;

    // create product types
    @RequestMapping(path = Constant.PRODUCT_TYPES_CREATE, method = RequestMethod.POST)
    public ResponseEntity<RestAPIResponse> createProductTypes(
            HttpServletRequest request,
            @RequestPart(value = "images", required = false) MultipartFile images,
            @RequestPart(value = "product_types_request")ProductTypeRequest productTypeRequest
            ) {
        AuthUser authUser = getAuthUserFromSession(request);
        if (authUser != null) {
            ProductTypes productTypes = productTypeService.findProductTypeByProductTypeNameAndStatus(productTypeRequest.getProductTypeName(), Constant.Status.ACTIVE.getValues());
            if (productTypes == null) {
                ProductTypes newProductType = new ProductTypes();
                Date curentDate = new Date();
                if (images != null) {
                    String fileName = productTypeRequest.getProductTypeName() +
                            CommonUtil.getFileExtension(images);

                    String url = fileUploadService.uploadFile(images, fileName);

                    String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                            .path("/files/")
                            .path(fileName)
                            .toUriString();

                    newProductType.setProductTypeImage(fileDownloadUri);
                    newProductType.setProductTypeName(productTypeRequest.getProductTypeName());
                    newProductType.setProductTypeDescription(productTypeRequest.getProductTypeDescription());
                    newProductType.setStatus(Constant.Status.ACTIVE.getValues());
                    newProductType.setCreateDate(DateUtil.convertToUTC(curentDate));
                    newProductType.setLastActivity(DateUtil.convertToUTC(curentDate));
                    return responseUtil.successResponse(productTypeService.saveProductType(newProductType));
                }
                else{
                    throw  new ApplicationException(APIStatus.ERR_IMAGE_EXITS);
                }
            }
            throw new ApplicationException(APIStatus.ERR_PRODUCT_TYPE_EXITS);

        }
        throw new ApplicationException(APIStatus.ERR_USER_NOT_FOUND);
    }
    // update product types
    @RequestMapping(path = Constant.PRODUCT_TYPES_UPDATE, method = RequestMethod.PUT)
    public ResponseEntity<RestAPIResponse> updateProductTypes(
            HttpServletRequest request,
            @RequestPart(value = "images", required = false) MultipartFile images,
            @RequestPart(value = "productTypeId", required = false) String productTypeId,
            @RequestPart(value = "product_types_request")ProductTypeRequest productTypeRequest
    ) {
        AuthUser authUser = getAuthUserFromSession(request);
        Date updateDate = new Date();
        if (authUser != null) {
            ProductTypes productTypes = productTypeService.findProductTypeByProductTypeId(productTypeId);
            if (productTypes != null) {
                if (!productTypeRequest.getProductTypeName().equals(productTypes.getProductTypeName())){
                    productTypes.setProductTypeName(productTypeRequest.getProductTypeName());
                }
                if (!productTypeRequest.getProductTypeDescription().equals(productTypes.getProductTypeDescription())){
                    productTypes.setProductTypeDescription(productTypeRequest.getProductTypeDescription());
                }
                productTypes.setLastActivity(DateUtil.convertToUTC(updateDate));
                productTypes.setStatus(Constant.Status.ACTIVE.getValues());
                productTypes.setProductTypeImage(productTypes.getProductTypeImage());
                if (images != null) {

                    String fileName = productTypeRequest.getProductTypeName() +
                            CommonUtil.getFileExtension(images);

                    String url = fileUploadService.uploadFile(images, fileName);

                    String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                            .path("/files/")
                            .path(fileName)
                            .toUriString();
                    productTypes.setProductTypeImage(fileDownloadUri);
                } else if (productTypeRequest.getImageUrl() != null){
                    productTypes.setProductTypeImage(productTypeRequest.getImageUrl());
                }
                return responseUtil.successResponse(productTypeService.saveProductType(productTypes));
            }
            throw new ApplicationException(APIStatus.ERR_PRODUCT_TYPE_NOT_FOUND);
        }
        throw new ApplicationException(APIStatus.ERR_USER_NOT_FOUND);
    }
    // get list product type sort search by status = 1
    @RequestMapping(path = Constant.TYPE_PRODUCT_LIST, method = RequestMethod.POST)
    public ResponseEntity<RestAPIResponse> getList(HttpServletRequest request, @RequestBody PagingRequestModel pagingRequestModel)
    {
        AuthUser authUser = getAuthUserFromSession(request);
        if (authUser != null) {
            Page<ProductTypes> productTypeList = productTypeService.pagingBoxProductType(pagingRequestModel,authUser.getLang());
            return responseUtil.successResponse(productTypeList);
        }
        throw new ApplicationException(APIStatus.ERR_USER_NOT_FOUND);
    }
    // change status 1 to 0
    @RequestMapping(path = Constant.TYPE_PRODUCT_CHANGE_STATUS,method = RequestMethod.PUT)
    public ResponseEntity<RestAPIResponse> changeStatus(HttpServletRequest request, @Param(value = "product_type_id") String product_type_id)
    {
        AuthUser authUser = getAuthUserFromSession(request);
        if (authUser != null) {
            ProductTypes productTypes = productTypeService.findProductTypeByProductTypeId(product_type_id);
            if (productTypes != null) {
                if (productTypes.getStatus() == Constant.Status.ACTIVE.getValues()) {
                    productTypes.setStatus(Constant.Status.DELETE.getValues());
                    return responseUtil.successResponse(productTypeService.saveProductType(productTypes));
                } else {
                    productTypes.setStatus(Constant.Status.ACTIVE.getValues());
                    return responseUtil.successResponse(productTypeService.saveProductType(productTypes));
                }
            }
            throw new ApplicationException(APIStatus.ERR_PRODUCT_TYPE_NOT_FOUND);
        }
        throw new ApplicationException(APIStatus.ERR_USER_NOT_FOUND);
    }
    // find one product type by id and status
    @RequestMapping(path = Constant.TYPE_PRODUCT_FIND_ONE, method = RequestMethod.GET)
    public ResponseEntity<RestAPIResponse> getOneProductType(HttpServletRequest request, @PathVariable(value = "id") String id)
    {
        AuthUser authUser = getAuthUserFromSession(request);
        if (authUser != null) {
            ProductTypes productTypes = productTypeService.findProductTypeIdAndStatus(id,Constant.Status.ACTIVE.getValues());
            if (productTypes != null){
                return responseUtil.successResponse(productTypes);
            }
            throw new ApplicationException(APIStatus.ERR_PRODUCT_TYPE_NOT_FOUND);
        } else {
            throw new ApplicationException(APIStatus.ERR_USER_NOT_FOUND);
        }
    }
    // list product type
    @RequestMapping(path = Constant.TYPE_PRODUCT_LIST_CLIENT, method = RequestMethod.GET)
    public ResponseEntity<RestAPIResponse> getListProductType(){
        List<ProductTypes> listProductType = productTypeService.getListProductType(Constant.Status.ACTIVE.getValues());
        return responseUtil.successResponse(listProductType);
    }
}
