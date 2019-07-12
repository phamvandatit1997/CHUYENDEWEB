import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {CustomerService} from '../../../service/customer.service';
import {Pattern} from '../../../../_util/constant/pattern.constant';
import {TranslateService} from '../../../service/translate.service';
import {Location} from '@angular/common';

@Component({
  selector: 'app-edit-customer',
  templateUrl: './detail-customer.component.html',
  styleUrls: ['./detail-customer.component.css']
})
export class DetailCustomerComponent implements OnInit {

  customer: any = {};
  emailPattern: any = Pattern.EMAIL_PATTERN;

  constructor(
    private location: Location,
    private route: ActivatedRoute,
    private customerService: CustomerService,
    private router: Router,
    private translate: TranslateService
  ) {}

  ngOnInit() {
    this.route.params.subscribe(params => {
        this.customerService.getCustomer(params['id']).subscribe(response => {
            this.customer = response;
        });
    });
  }
  backClicked(){
    this.location.back();
  }
}
