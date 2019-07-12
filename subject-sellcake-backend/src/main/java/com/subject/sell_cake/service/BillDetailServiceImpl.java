package com.subject.sell_cake.service;

import com.subject.sell_cake.model.entity.BillDetails;
import com.subject.sell_cake.repository.BillDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BillDetailServiceImpl implements BillDetailService{

    @Autowired
    BillDetailsRepository billDetailsRepository;

    @Override
    public BillDetails saveBillDetail(BillDetails billDetails) {
        return billDetailsRepository.save(billDetails);
    }

    @Override
    public List<BillDetails> listBillDetail(String billId) {
        return billDetailsRepository.getBillDetailFromBillId(billId);
    }

    @Override
    public void deleteBillDetail(BillDetails billDetails) {
        billDetailsRepository.delete(billDetails);
    }
}
