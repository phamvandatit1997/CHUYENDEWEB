import { Component, OnInit } from '@angular/core';
import {Customers} from '../../../model/customers';
import {CustomerService} from '../../../service/customer.service';
import {Router} from '@angular/router';
import {TranslateService} from '../../../service/translate.service';
import {CustomToastrService} from '../../../service/custom-toastr.service';

@Component({
  selector: 'app-list-customer',
  templateUrl: './list-customer.component.html',
  styleUrls: ['./list-customer.component.css']
})
export class ListCustomerComponent implements OnInit {
  pagingRequest: any = {
    searchKey: "",
    sortCase:1,
    ascSort: false,
    pageNumber: 1,
    pageSize: 10
  };
  customers: Array<Customers> = [];
  collectionSize: number;

  constructor(
    private customerService: CustomerService,
    private router: Router,
    private translate: TranslateService,
    private toastrService: CustomToastrService
  ) { }

  ngOnInit() {
    this.getlistCustomer();
  }
  // get list-product-type customer
  getlistCustomer() {
    this.customerService.getListCustomer(this.pagingRequest).subscribe(response => {
        this.customers = response.content;
        this.collectionSize = response.totalElements;
    });
  }
  // change status customer
  changeStatusCustomer(id: string) {
      this.customerService.changeStatusCustomer(id).subscribe(response =>{
        this.getlistCustomer();
      });
  }
}
