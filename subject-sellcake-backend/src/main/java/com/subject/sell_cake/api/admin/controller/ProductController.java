package com.subject.sell_cake.api.admin.controller;

import com.subject.sell_cake.api.AbstractBasedAPI;
import com.subject.sell_cake.api.admin.request.PagingRequestModel;
import com.subject.sell_cake.api.admin.request.ProductRequest;
import com.subject.sell_cake.api.admin.request.ProductTypeRequest;
import com.subject.sell_cake.auth.AuthUser;
import com.subject.sell_cake.exception.ApplicationException;
import com.subject.sell_cake.model.RestAPIResponse;
import com.subject.sell_cake.model.entity.Product;
import com.subject.sell_cake.response.APIStatus;
import com.subject.sell_cake.service.FileUploadService;
import com.subject.sell_cake.service.ProductService;
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
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(path = Constant.PRODUCT)
public class ProductController extends AbstractBasedAPI {

    @Autowired
    ProductService productService;

    @Autowired
    FileUploadService fileUploadService;

    // create product
    @RequestMapping(path = Constant.PRODUCT_CREATE, method = RequestMethod.POST)
    public ResponseEntity<RestAPIResponse> createProduct
    (HttpServletRequest request,
     @RequestPart(value = "images", required = false) MultipartFile images,
     @RequestPart(value = "product") ProductRequest productRequest
    ) {
        AuthUser authUser = getAuthUserFromSession(request);
        if (authUser != null) {
            Date currentDate = new Date();
            Product product = new Product();
            product.setProductName(productRequest.getProductName());
            product.setProductTypeId(productRequest.getProductTypeId());
            product.setDescription(productRequest.getDescription());
            product.setUnitPrice(productRequest.getUnitPrice());
            product.setPromotionPrice(productRequest.getPromotionPrice());
            product.setUnit(productRequest.getUnit());
            product.setCreateDate(DateUtil.convertToUTC(currentDate));
            product.setLastActivity(DateUtil.convertToUTC(currentDate));
            product.setStatus(Constant.Status.ACTIVE.getValues());
            if (images != null) {
                String fileName = "product_" + productRequest.getProductName() +
                        CommonUtil.getFileExtension(images);

                String url = fileUploadService.uploadFile(images, fileName);

                String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                        .path("/files/")
                        .path(fileName)
                        .toUriString();

                product.setImages(fileDownloadUri);
            } else if (productRequest.getImages() != null) {
                product.setImages(productRequest.getImages());
            }
            return responseUtil.successResponse(productService.saveProduct(product));
        }
        throw new ApplicationException(APIStatus.ERR_USER_NOT_FOUND);
    }

    //change status product
    @RequestMapping(path = Constant.PRODUCT_CHANGE_STATUS, method = RequestMethod.PUT)
    public ResponseEntity<RestAPIResponse> changeStatus(HttpServletRequest request, @Param(value = "product_id") String product_id) {
        AuthUser authUser = getAuthUserFromSession(request);
        if (authUser != null) {
            Product product = productService.getDetailProduct(product_id);
            if (product != null) {
                if (product.getStatus() == Constant.Status.ACTIVE.getValues()) {
                    product.setStatus(Constant.Status.DELETE.getValues());
                    return responseUtil.successResponse(productService.saveProduct(product));
                } else {
                    product.setStatus(Constant.Status.ACTIVE.getValues());
                    return responseUtil.successResponse(productService.saveProduct(product));
                }
            }
            throw new ApplicationException(APIStatus.ERR_PRODUCT_NOT_FOUND);
        }
        throw new ApplicationException(APIStatus.ERR_USER_NOT_FOUND);
    }

    // update product
    @RequestMapping(path = Constant.PRODUCT_UPDATE, method = RequestMethod.PUT)
    public ResponseEntity<RestAPIResponse> updateProduct(
            HttpServletRequest request,
            @RequestPart(value = "images", required = false) MultipartFile images,
            @RequestPart(value = "product_id", required = false) String product_id,
            @RequestPart(value = "product_request") ProductRequest productRequest
    ) {
        AuthUser authUser = getAuthUserFromSession(request);
        Date updateDate = new Date();
        if (authUser != null) {
            Product product = productService.getDetailProduct(product_id);
            if (product != null) {
                if (!productRequest.getProductName().equals(product.getProductName())) {
                    product.setProductName(productRequest.getProductName());
                }
                if (!productRequest.getDescription().equals(product.getDescription())) {
                    product.setDescription(productRequest.getDescription());
                }
                if (productRequest.getUnitPrice() != product.getUnitPrice()) {
                    product.setUnitPrice(productRequest.getUnitPrice());
                }
                if (productRequest.getPromotionPrice() != product.getPromotionPrice()) {
                    product.setPromotionPrice(productRequest.getPromotionPrice());
                }
                if (!productRequest.getUnit().equals(product.getUnit())) {
                    product.setUnit(productRequest.getUnit());
                }
                product.setLastActivity(DateUtil.convertToUTC(updateDate));
                product.setStatus(Constant.Status.ACTIVE.getValues());
                product.setImages(productRequest.getImages());
                if (images != null) {
                    String fileName = "product_" + productRequest.getProductName() +
                            CommonUtil.getFileExtension(images);

                    String url = fileUploadService.uploadFile(images, fileName);

                    String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                            .path("/files/")
                            .path(fileName)
                            .toUriString();

                    product.setImages(fileDownloadUri);
                } else if (productRequest.getImages() != null){
                    product.setImages(productRequest.getImages());
                }
                return responseUtil.successResponse(productService.saveProduct(product));
            }
            throw new ApplicationException(APIStatus.ERR_PRODUCT_NOT_FOUND);
        }
        throw new ApplicationException(APIStatus.ERR_USER_NOT_FOUND);
    }

    // get list product
    @RequestMapping(path = Constant.PRODUCT_LIST, method = RequestMethod.POST)
    public ResponseEntity<RestAPIResponse> getListProduct(HttpServletRequest request, @RequestBody PagingRequestModel pagingRequestModel) {
        AuthUser authUser = getAuthUserFromSession(request);
        if (authUser != null) {
            Page<Product> pagingBoxProduct = productService.pagingBoxProduct(pagingRequestModel, authUser.getLang());
            return responseUtil.successResponse(pagingBoxProduct);
        }
        throw new ApplicationException(APIStatus.ERR_USER_NOT_FOUND);
    }

    // get list product client
    @RequestMapping(path = Constant.PRODUCT_CLIENT_LIST, method = RequestMethod.POST)
    public ResponseEntity<RestAPIResponse> getListClientProduct(@RequestBody PagingRequestModel pagingRequestModel) {
        Page<Product> pagingBoxProduct = productService.pagingBoxProductClient(pagingRequestModel,"vi");
        return responseUtil.successResponse(pagingBoxProduct);
    }

    // get product
    @RequestMapping(path = Constant.PRODUCT_FIND_ONE, method = RequestMethod.GET)
    public ResponseEntity<RestAPIResponse> getProduct(HttpServletRequest request, @PathVariable String id) {
        AuthUser authUser = getAuthUserFromSession(request);
        if (authUser != null) {
            Product product = productService.getDetailProduct(id);
            if (product == null) {
                throw new ApplicationException(APIStatus.ERR_PRODUCT_NOT_FOUND);
            } else {
                return responseUtil.successResponse(product);
            }
        } else {
            throw new ApplicationException(APIStatus.ERR_USER_NOT_FOUND);
        }
    }

    // get product
    @RequestMapping(path = Constant.PRODUCT_CLIENT_DETAIL, method = RequestMethod.GET)
    public ResponseEntity<RestAPIResponse> getProductDetail(@PathVariable String id) {
        Product product = productService.getDetailProduct(id);
        if (product == null) {
            throw new ApplicationException(APIStatus.ERR_PRODUCT_NOT_FOUND);
        } else {
            return responseUtil.successResponse(product);
        }
    }

    // get list product by product type id
    @RequestMapping(path = Constant.PRODUCT_BY_PRODUCTTYPEID, method = RequestMethod.GET)
    public ResponseEntity<RestAPIResponse> getProductByProductTypeId(@PathVariable String id) {
        List<Product> list = productService.findProductByProductTypeId(id);
        return responseUtil.successResponse(list);
    }
}
