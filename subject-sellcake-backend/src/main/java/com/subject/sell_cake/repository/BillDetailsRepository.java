package com.subject.sell_cake.repository;

import com.subject.sell_cake.model.entity.*;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface BillDetailsRepository extends PagingAndSortingRepository<BillDetails, String>,JpaSpecificationExecutor<BillDetails> {

    //get list bill detail from bill id
    @Query(value = "SELECT bd FROM BillDetails bd WHERE bd.billId = :billId")
    public List<BillDetails> getBillDetailFromBillId(@Param(value = "billId") String billId);
}
