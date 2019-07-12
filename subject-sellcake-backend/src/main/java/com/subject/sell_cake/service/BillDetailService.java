package com.subject.sell_cake.service;

import com.subject.sell_cake.model.entity.BillDetails;

import java.util.List;

public interface BillDetailService {
    // save bill detail
    public BillDetails saveBillDetail(BillDetails billDetails);
    // find list bill detail by bill id
    public List<BillDetails> listBillDetail(String billId);
    // delete bill detail by bill id
    public void deleteBillDetail(BillDetails billDetails);
}
