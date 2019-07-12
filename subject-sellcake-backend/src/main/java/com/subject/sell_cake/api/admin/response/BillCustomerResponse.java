package com.subject.sell_cake.api.admin.response;

import com.subject.sell_cake.model.entity.Bills;
import com.subject.sell_cake.model.entity.Customers;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class BillCustomerResponse {
    private Bills bill;
    private List<BillDetailsResponse> listBillProduct;
    private Customers customers;

    public Bills getBill() {
        return bill;
    }

    public void setBill(Bills bill) {
        this.bill = bill;
    }

    public List<BillDetailsResponse> getListBillProduct() {
        return listBillProduct;
    }

    public void setListBillProduct(List<BillDetailsResponse> listBillProduct) {
        this.listBillProduct = listBillProduct;
    }

    public Customers getCustomers() {
        return customers;
    }

    public void setCustomers(Customers customers) {
        this.customers = customers;
    }
}
