package com.subject.sell_cake.service;
import com.subject.sell_cake.api.admin.request.PagingRequestModel;
import com.subject.sell_cake.model.entity.BillDetails;
import com.subject.sell_cake.model.entity.Bills;
import org.springframework.data.domain.Page;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface BillsService {
    // save bill
    public Bills saveBill(Bills bills);
    // paging box sort search
    public Page<Bills> pagingBoxBill(PagingRequestModel pagingRequestModel, String lang);
    // find bill by billId and status
    public Bills findBillByBillIdAndStatus(String billId, int status);
    // find bill by id
    public Bills getBillFromBillId(String id);
    // get list bill detail from bill id
    public List<BillDetails> getBillDetailFromBillId(String billId);
    // delete bill by bill id
    public void deleteBill(Bills bills);
    // find all bill
    public List<Bills> findAll();
    // get list bill by customer id
    public Bills findBillByCustomerId(String customerId);
}
