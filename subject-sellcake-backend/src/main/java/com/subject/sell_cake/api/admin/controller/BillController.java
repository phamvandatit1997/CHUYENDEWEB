package com.subject.sell_cake.api.admin.controller;

import com.subject.sell_cake.api.AbstractBasedAPI;
import com.subject.sell_cake.api.admin.request.BillRequest;
import com.subject.sell_cake.api.admin.request.PagingRequestModel;
import com.subject.sell_cake.api.admin.response.BillCustomerResponse;
import com.subject.sell_cake.api.admin.response.BillDetailsResponse;
import com.subject.sell_cake.auth.AuthUser;
import com.subject.sell_cake.exception.ApplicationException;
import com.subject.sell_cake.model.RestAPIResponse;
import com.subject.sell_cake.model.entity.*;
import com.subject.sell_cake.response.APIStatus;
import com.subject.sell_cake.service.*;
import com.subject.sell_cake.util.Constant;
import com.subject.sell_cake.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(path = Constant.BILLS)
public class BillController extends AbstractBasedAPI {
    @Autowired
    ServletContext context;

    @Autowired
    ProductService productService;

    @Autowired
    ProductTypeService productTypeService;

    @Autowired
    BillsService billsService;

    @Autowired
    CustomerService customerService;

    @Autowired
    BillDetailService billDetailService;

    // create bill
    @RequestMapping(path = Constant.BILL_CREATE, method = RequestMethod.POST)
    public ResponseEntity<RestAPIResponse> createBill(HttpServletRequest request, @RequestBody BillRequest billRequest) {
        AuthUser authUser = getAuthUserFromSession(request);
        Customers customers = customerService.findCustomerByEmailAndStatus(authUser.getEmail(), Constant.Status.ACTIVE.getValues());
        if (authUser != null) {
            Date currentDate = new Date();
            Calendar c = Calendar.getInstance();
            c.setTime(currentDate);
            c.roll(Calendar.DATE, 5);
            Bills bills = billsService.findBillByCustomerId(customers.getCustomerId());
            if (bills == null){
                Bills newBill = new Bills();
                newBill.setCustomerId(authUser.getId());
                newBill.setTotal(billRequest.getSumPrice());
                newBill.setPayment("COD");
                newBill.setNote("Giao giao hàng nhanh nhất");
                newBill.setDateShiper(c.getTime());
                newBill.setDateOrder(DateUtil.convertToUTC(currentDate));
                newBill.setCreateDate(DateUtil.convertToUTC(currentDate));
                newBill.setLastActivity(DateUtil.convertToUTC(currentDate));
                newBill.setStatus(Constant.Status.ACTIVE.getValues());
                billsService.saveBill(newBill);
                List<Cart> listCart = billRequest.getListCart();
                for (Cart cart : listCart) {
                    BillDetails billDetails = new BillDetails();
                    billDetails.setBillId(newBill.getBillId());
                    billDetails.setCreateDate(DateUtil.convertToUTC(currentDate));
                    billDetails.setLastActivity(DateUtil.convertToUTC(currentDate));
                    billDetails.setProductId(cart.getProductId());
                    billDetails.setQuantity(cart.getQuantity());
                    billDetails.setUnitPrice(cart.getPrice());
                    billDetails.setSumPrice(cart.getQuantity() * cart.getPrice());
                    billDetails.setStatus(Constant.Status.ACTIVE.getValues());
                    billDetailService.saveBillDetail(billDetails);
                }
                return responseUtil.successResponse(APIStatus.OK);
            } else {
                bills.setTotal(bills.getTotal() + billRequest.getSumPrice());
                billsService.saveBill(bills);
                List<Cart> listCart = billRequest.getListCart();
                for (Cart cart : listCart) {
                    BillDetails billDetails = new BillDetails();
                    billDetails.setBillId(bills.getBillId());
                    billDetails.setCreateDate(DateUtil.convertToUTC(currentDate));
                    billDetails.setLastActivity(DateUtil.convertToUTC(currentDate));
                    billDetails.setProductId(cart.getProductId());
                    billDetails.setQuantity(cart.getQuantity());
                    billDetails.setUnitPrice(cart.getPrice());
                    billDetails.setSumPrice(cart.getQuantity() * cart.getPrice());
                    billDetails.setStatus(Constant.Status.ACTIVE.getValues());
                    billDetailService.saveBillDetail(billDetails);
                }
                return responseUtil.successResponse(APIStatus.OK);
            }
        }
        throw new ApplicationException(APIStatus.ERR_USER_NOT_FOUND);
    }

    // paging sort search
    @RequestMapping(path = Constant.BILL_LIST, method = RequestMethod.POST)
    public ResponseEntity<RestAPIResponse> pagingBoxBill(HttpServletRequest request, @RequestBody PagingRequestModel pagingRequestModel) {
        AuthUser authUser = getAuthUserFromSession(request);
        if (authUser != null) {
            Page<Bills> pagingBox = billsService.pagingBoxBill(pagingRequestModel, authUser.getLang());
            return responseUtil.successResponse(pagingBox);
        }
        throw new ApplicationException(APIStatus.ERR_USER_NOT_FOUND);
    }
    // bill detail by customer id
    @RequestMapping(path = Constant.BILL_DETAIL_CUSTOMERID, method = RequestMethod.GET)
    public ResponseEntity<RestAPIResponse> billDetailByCustomerId(HttpServletRequest request) {
        AuthUser authUser = getAuthUserFromSession(request);
        Customers customers = customerService.findCustomerByEmailAndStatus(authUser.getEmail(), Constant.Status.ACTIVE.getValues());
        Bills bills = billsService.findBillByCustomerId(customers.getCustomerId());
        if (bills != null) {
            List<BillDetails> billDetails = billsService.getBillDetailFromBillId(bills.getBillId());
            List<BillDetailsResponse> listBillProduct = new ArrayList<BillDetailsResponse>();
            for (int i = 0; i < billDetails.size(); i++) {
                Product product = productService.getDetailProduct(billDetails.get(i).getProductId());
                ProductTypes productTypes = productTypeService.findProductTypeByProductTypeId(product.getProductTypeId());

                // bill detail
                BillDetailsResponse billDetailsResponse = new BillDetailsResponse();
                billDetailsResponse.setBills(bills);
                billDetailsResponse.setProduct(product);
                billDetailsResponse.setProductTypes(productTypes);
                billDetailsResponse.setQuantity(billDetails.get(i).getQuantity());
                billDetailsResponse.setUnitPrice(billDetails.get(i).getUnitPrice());
                billDetailsResponse.setSumPrice(billDetails.get(i).getSumPrice());
                // add bill detail to list bill product
                listBillProduct.add(billDetailsResponse);
            }
            // bill detail customer from bill id
            BillCustomerResponse billCustomerResponse = new BillCustomerResponse();
            billCustomerResponse.setBill(bills);
            billCustomerResponse.setListBillProduct(listBillProduct);
            billCustomerResponse.setCustomers(customers);
            return responseUtil.successResponse(billCustomerResponse);
        } else {
            throw new ApplicationException(APIStatus.ERR_BILL_NOT_FOUND);
        }
    }
    // bill detail
    @RequestMapping(path = Constant.BILL_DETAIL, method = RequestMethod.GET)
    public ResponseEntity<RestAPIResponse> getBillDetail(HttpServletRequest request, @Param(value = "billId") String billId) {
        AuthUser authUser = getAuthUserFromSession(request);
        if (authUser != null) {
            Bills bills = billsService.getBillFromBillId(billId);
            if (bills != null) {
                List<BillDetails> billDetails = billsService.getBillDetailFromBillId(billId);
                List<BillDetailsResponse> listBillProduct = new ArrayList<BillDetailsResponse>();
                for (int i = 0; i < billDetails.size(); i++) {
                    Product product = productService.getDetailProduct(billDetails.get(i).getProductId());
                    ProductTypes productTypes = productTypeService.findProductTypeByProductTypeId(product.getProductTypeId());

                    // bill detail
                    BillDetailsResponse billDetailsResponse = new BillDetailsResponse();
                    billDetailsResponse.setBills(bills);
                    billDetailsResponse.setProduct(product);
                    billDetailsResponse.setProductTypes(productTypes);
                    billDetailsResponse.setQuantity(billDetails.get(i).getQuantity());
                    billDetailsResponse.setUnitPrice(billDetails.get(i).getUnitPrice());
                    billDetailsResponse.setSumPrice(billDetails.get(i).getSumPrice());
                    // add bill detail to list bill product
                    listBillProduct.add(billDetailsResponse);
                }
                Customers customers = customerService.findCustomerByCustomerId(bills.getCustomerId());
                // bill detail customer from bill id
                BillCustomerResponse billCustomerResponse = new BillCustomerResponse();
                billCustomerResponse.setBill(bills);
                billCustomerResponse.setListBillProduct(listBillProduct);
                billCustomerResponse.setCustomers(customers);
                return responseUtil.successResponse(billCustomerResponse);
            } else {
                throw new ApplicationException(APIStatus.ERR_BILL_NOT_FOUND);
            }
        }
        throw new ApplicationException(APIStatus.ERR_USER_NOT_FOUND);
    }

    // change status bill
    @RequestMapping(path = Constant.BILL_CHANGE_STATUS, method = RequestMethod.PUT)
    public ResponseEntity<RestAPIResponse> changeStatus(HttpServletRequest request, @Param(value = "billId") String billId) {
        AuthUser authUser = getAuthUserFromSession(request);
        if (authUser != null) {
            Bills bill = billsService.getBillFromBillId(billId);
            if (bill != null) {
                if (bill.getStatus() == Constant.Status.ACTIVE.getValues()) {
                    bill.setStatus(Constant.Status.DELETE.getValues());
                    return responseUtil.successResponse(billsService.saveBill(bill));
                } else {
                    bill.setStatus(Constant.Status.ACTIVE.getValues());
                    return responseUtil.successResponse(billsService.saveBill(bill));
                }
            } else {
                throw new ApplicationException(APIStatus.ERR_BILL_NOT_FOUND);
            }
        } else {
            throw new ApplicationException(APIStatus.ERR_USER_NOT_FOUND);
        }
    }

    // delete bill and bill detail
    @RequestMapping(value = Constant.BILL_DELETE, method = RequestMethod.GET)
    public ResponseEntity<RestAPIResponse> deleteBillAndBillDetail(HttpServletRequest request, @PathVariable(value = "id") String billId) {
        AuthUser authUser = getAuthUserFromSession(request);
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        if (authUser != null) {
            List<BillDetails> listBillDetail = billDetailService.listBillDetail(billId);
            for (BillDetails billDetails : listBillDetail) {
                billDetailService.deleteBillDetail(billDetails);
            }
            Bills bills = billsService.findBillByBillIdAndStatus(billId, Constant.Status.ACTIVE.getValues());
            String dateO = formatter.format(bills.getDateOrder());
            System.out.println(dateO);
            billsService.deleteBill(bills);
            return responseUtil.successResponse(APIStatus.OK);
        } else {
            throw new ApplicationException(APIStatus.ERR_USER_NOT_FOUND);
        }
    }
}
