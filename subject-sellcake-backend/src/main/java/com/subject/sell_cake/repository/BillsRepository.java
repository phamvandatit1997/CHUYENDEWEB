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
public interface BillsRepository extends PagingAndSortingRepository<Bills, String>, JpaSpecificationExecutor<Bills> {
    // find bill by bill id and status
    public Bills findBillsByBillIdAndStatus(String billId, int status);

    // find bill by bill
    @Query(value = "SELECT b FROM Bills b WHERE b.billId = :billId")
    public Bills getBillFromBillId(@Param(value = "billId") String id);

    //find all bill
    public List<Bills> findAll();

    // find bill by customer id
    public Bills findBillsByCustomerId(String customerId);

}
