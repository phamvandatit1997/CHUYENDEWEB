package com.subject.sell_cake.service;

import com.subject.sell_cake.api.admin.request.PagingRequestModel;
import com.subject.sell_cake.model.entity.BillDetails;
import com.subject.sell_cake.model.entity.Bills;

import com.subject.sell_cake.repository.BillDetailsRepository;
import com.subject.sell_cake.repository.BillsRepository;
import com.subject.sell_cake.repository.specification.BillSpecification;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.springframework.data.domain.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;


@Component
public class BillsServiceImpl implements BillsService {

    // bill detail repository
    @Autowired
    BillDetailsRepository billDetailsRepository;
    // bill repository
    @Autowired
    BillsRepository billsRepository;


    @Override
    public Bills saveBill(Bills bills) {
        return billsRepository.save(bills);
    }

    @Override
    public Page<Bills> pagingBoxBill(PagingRequestModel pagingRequestModel, String lang) {
        BillSpecification dfs = new BillSpecification(pagingRequestModel.getSearchKey(), pagingRequestModel.getSortCase(), pagingRequestModel.isAscSort(), lang);
        PageRequest pageRequest = new PageRequest((pagingRequestModel.getPageNumber() - 1), pagingRequestModel.getPageSize());
        return billsRepository.findAll(dfs, pageRequest);
    }

    @Override
    public Bills findBillByBillIdAndStatus(String billId, int status) {
        return billsRepository.findBillsByBillIdAndStatus(billId, status);
    }

    @Override
    public Bills getBillFromBillId(String id) {
        return billsRepository.getBillFromBillId(id);
    }

    @Override
    public List<BillDetails> getBillDetailFromBillId(String billId) {
        return billDetailsRepository.getBillDetailFromBillId(billId);
    }

    @Override
    public void deleteBill(Bills bills) {
        billsRepository.delete(bills);
    }

    @Override
    public List<Bills> findAll() {
        return billsRepository.findAll();
    }


    @Override
    public Bills findBillByCustomerId(String customerId) {
        return billsRepository.findBillsByCustomerId(customerId);
    }


}
