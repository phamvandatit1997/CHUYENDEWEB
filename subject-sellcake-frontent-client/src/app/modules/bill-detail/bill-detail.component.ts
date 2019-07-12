import { Component, OnInit } from '@angular/core';
import {BillDetail} from '../models/bill-detail';
import {Customer} from '../models/customer';
import {BillService} from '../../service/bill.service';
import {TranslateService} from '../../service/translate.service';

@Component({
  selector: 'app-bill-detail',
  templateUrl: './bill-detail.component.html',
  styleUrls: ['./bill-detail.component.css']
})
export class BillDetailComponent implements OnInit {

  customerInfo: Customer;
  // @ts-ignore
  bill: Bills;
  billId: string;
  dateOrder: string;
  total: number;
  payment: string;
  dateShiper: string;
  billDetails: Array<BillDetail> = [];

  constructor(
    private billService: BillService,
    private translateService:TranslateService
  ) { }

  ngOnInit() {
      this.billService.billDetailByCustomerId().subscribe(response => {
        this.customerInfo = response.customers;
        this.bill = response.bill;
        if (this.bill == null) {
          this.billId = '';
          this.dateOrder = '';
          this.total = 0;
          this.payment = '';
          this.dateShiper = '';
        } else {
          this.billId = response.bill.billId;
          this.dateOrder = response.bill.dateOrder;
          this.total = response.bill.total;
          this.payment = response.bill.payment;
          this.dateShiper = response.bill.dateShiper;
        }
        this.billDetails = response.listBillProduct;
      });
  }

}
