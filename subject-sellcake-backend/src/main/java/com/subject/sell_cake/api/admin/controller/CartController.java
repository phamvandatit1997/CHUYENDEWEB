package com.subject.sell_cake.api.admin.controller;

import com.subject.sell_cake.api.AbstractBasedAPI;
import com.subject.sell_cake.api.admin.response.CartResponse;
import com.subject.sell_cake.auth.AuthUser;
import com.subject.sell_cake.model.RestAPIResponse;
import com.subject.sell_cake.model.entity.Cart;
import com.subject.sell_cake.model.entity.Customers;
import com.subject.sell_cake.model.entity.Product;
import com.subject.sell_cake.response.APIStatus;
import org.springframework.data.repository.query.Param;
import com.subject.sell_cake.service.AuthService;
import com.subject.sell_cake.service.CartService;
import com.subject.sell_cake.service.CustomerService;
import com.subject.sell_cake.service.ProductService;
import com.subject.sell_cake.util.Constant;
import com.subject.sell_cake.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(path = Constant.CART)
public class CartController extends AbstractBasedAPI {

    @Autowired
    CartService cartService;

    @Autowired
    CustomerService customerService;

    @Autowired
    AuthService authService;

    @Autowired
    ProductService productService;

    // save cart product
    @RequestMapping(path = Constant.CART_ADD, method = RequestMethod.GET)
    public ResponseEntity<RestAPIResponse> addCart(HttpServletRequest request, @Param(value ="product_id") String product_id) {
        AuthUser authUser = getAuthUserFromSession(request);
        Customers customers = customerService.findCustomerByEmailAndStatus(authUser.getEmail(), Constant.Status.ACTIVE.getValues());
        Product product = productService.findProductByProductId(product_id);
        Date currentDate = new Date();
        Cart existCart = cartService.findCartByProductId(product_id);
        if (existCart == null){
            Cart cart = new Cart();
            cart.setCustomerId(customers.getCustomerId());
            cart.setProductId(product.getProductId());
            cart.setImageProduct(product.getImages());
            if (product.getPromotionPrice() == 0) {
                cart.setPrice(product.getUnitPrice());
                cart.setSumPrice(product.getUnitPrice());
            } else {
                cart.setPrice(product.getPromotionPrice());
                cart.setSumPrice(product.getPromotionPrice());
            }
            cart.setQuantity(1);
            cart.setProductName(product.getProductName());
            cart.setCreateDate(DateUtil.convertToUTC(currentDate));
            cart.setLastActivity(DateUtil.convertToUTC(currentDate));
            return responseUtil.successResponse(cartService.saveCart(cart));
        } else {
            if (product.getPromotionPrice() != 0) {
                existCart.setSumPrice(product.getPromotionPrice() * (existCart.getQuantity() + 1));
            } else {
                existCart.setSumPrice(product.getUnitPrice() * (existCart.getQuantity() + 1));
            }
            existCart.setQuantity(existCart.getQuantity() + 1);
            return responseUtil.successResponse(cartService.saveCart(existCart));
        }
    }

    // get list cart
    @RequestMapping(path = Constant.CART_GET, method = RequestMethod.GET)
    public ResponseEntity<RestAPIResponse> getListCart(HttpServletRequest request) {
        int sumPrice = 0;
        int sumQuantity = 0;
        AuthUser authUser = getAuthUserFromSession(request);
        Customers customers = customerService.findCustomerByEmailAndStatus(authUser.getEmail(), Constant.Status.ACTIVE.getValues());
        List<Cart> carts = cartService.findCartByCustomerId(customers.getCustomerId());
        CartResponse cartResponse = new CartResponse();
        cartResponse.setListCart(carts);
        for (Cart cart: carts) {
            sumPrice = cartResponse.getSumPrice() + cart.getSumPrice();
            cartResponse.setSumPrice(sumPrice);
            sumQuantity += cart.getQuantity();
            cartResponse.setNumberProduct(sumQuantity);
        }
        return responseUtil.successResponse(cartResponse);
    }

    // delete quantity product
    @RequestMapping(value = Constant.CART_DELETE_ONE, method = RequestMethod.GET)
    public ResponseEntity<RestAPIResponse> deleteOneProductInCart(@PathVariable(value = "id") String productId) {
        Cart cart = cartService.findCartByProductId(productId);
        if (cart.getQuantity() > 1) {
            int newQuantity = cart.getQuantity() - 1;
            cart.setSumPrice(cart.getPrice() * newQuantity);
            cart.setQuantity(newQuantity);
        }
        return responseUtil.successResponse(cartService.saveCart(cart));
    }
    // delete all product in cart
    @RequestMapping(path = Constant.CART_DELETE_ALL, method = RequestMethod.GET)
    public ResponseEntity<RestAPIResponse> deleteAllProductInCart(@PathVariable(value = "id") String productId) {
        Cart cart =cartService.findCartByProductId(productId);
        cartService.delete(cart);
        return responseUtil.successResponse(APIStatus.OK);
    }
    // delete all cart by customer
    @RequestMapping(path = Constant.CART_DELETE_ALL_BY_CUSTOMER, method = RequestMethod.GET)
    public ResponseEntity<RestAPIResponse> deleteAllByCustomerId(HttpServletRequest request) {
        AuthUser authUser = getAuthUserFromSession(request);
        Customers customers = customerService.findCustomerByEmailAndStatus(authUser.getEmail(), Constant.Status.ACTIVE.getValues());
        List<Cart> cartList = cartService.findCartByCustomerId(customers.getCustomerId());
        for (Cart cart: cartList) {
            cartService.delete(cart);
        }
        return responseUtil.successResponse(APIStatus.OK);
    }
}
