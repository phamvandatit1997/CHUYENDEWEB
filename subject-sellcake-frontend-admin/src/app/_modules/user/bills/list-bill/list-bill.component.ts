import {Component, OnInit} from '@angular/core';
import {Bills} from '../../../model/bills';
import {BillsService} from '../../../service/bills.service';
import {TranslateService} from '../../../service/translate.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-list-bill',
  templateUrl: './list-bill.component.html',
  styleUrls: ['./list-bill.component.css']
})
export class ListBillComponent implements OnInit {

  pagingRequest: any = {
    searchKey: '',
    sortCase: 1,
    ascSort: false,
    pageNumber: 1,
    pageSize: 10
  };

  bills: Array<Bills> = [];
  collectionSize: number;

  constructor(
    private billService: BillsService,
    private translate: TranslateService,
    private router: Router,
  ) {
  }

  ngOnInit() {

   this.getListBill();
  }

  // paging sort search bills
  getListBill() {
    this.billService.getListBill(this.pagingRequest)
      .subscribe(response => {
        this.bills = response.content;
        this.collectionSize = response.totalElements;
      });
  }

  // change status
  changeBillStatus(id: string) {
    this.billService.changeBillStatus(id).subscribe(res => {
      if (res.status === 200) {
        this.getListBill();
        this.router.navigateByUrl('admin/bill/list');
      }
    });
  }

  // get bills by payment
  getBillsByPayment(payment: string) {
    this.billService.getBillsByPayment(payment).subscribe(res => {
      if (res.status === 200) {
        this.bills = res.data;
        this.router.navigateByUrl('admin/bill/list');
      }
    });
  }

  // export excel
  exportExcel() {
    this.billService.exportExcel().subscribe(res => {
      console.log(res);
      if (res.status === 200) {
        this.getListBill();
        this.router.navigateByUrl('admin/bill/list');
      }
    });
  }
}
