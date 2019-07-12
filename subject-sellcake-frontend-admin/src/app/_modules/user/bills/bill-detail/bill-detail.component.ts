import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {BillDetailService} from '../../../service/bill-detail.service';
import {Customers} from '../../../model/customers';
import {Bills} from '../../../model/bills';
import {TranslateService} from '../../../service/translate.service';
import {BillDetail} from '../../../model/bill-detail';
import * as jsPDF from 'jspdf';
import html2canvas from 'html2canvas';

@Component({
  selector: 'app-bill-detail',
  templateUrl: './bill-detail.component.html',
  styleUrls: ['./bill-detail.component.css']
})
export class BillDetailComponent implements OnInit {

  // @ts-ignore
  customerInfo: Customers = {};
  // @ts-ignore
  bill: Bills;
  customerName: string;
  billId: string;
  dateOrder: string;
  total: number;
  payment: string;
  dateShiper: string;
  billDetails: Array<BillDetail> = [];
  constructor(
    private route: ActivatedRoute,
    private billDetailService: BillDetailService,
    private translate: TranslateService
  ) {
  }

  ngOnInit() {
    this.route.params.subscribe(params => {
      this.billDetailService.billDetail(params['id'])
        .subscribe(response => {
          // console.log(response);
          this.customerInfo = response.customers;
          this.customerName = this.customerInfo.customerName;
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
    });
  }

  printBill() {
    var sourceHTML = document.getElementById("content").innerHTML;
    var source = 'data:application/vnd.ms-word;charset=utf-8,' + encodeURIComponent(sourceHTML);
    var fileDownload = document.createElement("a");
    document.body.appendChild(fileDownload);
    fileDownload.href = source;
    fileDownload.download = this.customerName + '.doc';
    fileDownload.click();
    document.body.removeChild(fileDownload);
  }
}
