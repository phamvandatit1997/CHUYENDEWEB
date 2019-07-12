import { Component, OnInit } from '@angular/core';
import {Pattern} from '../../../util/constant/pattern.constant';
import {CustomerService} from '../../../service/customer.service';
import {Router} from '@angular/router';
import {Location} from '@angular/common';

@Component({
  selector: 'app-customer-login',
  templateUrl: './customer-login.component.html',
  styleUrls: ['./customer-login.component.css']
})
export class CustomerLoginComponent implements OnInit {

  url: string = "/cake/home";
  customerLoginInfo: any = {
      email: '',
      password: ''
  };
  emailPattent: any = Pattern.EMAIL_PATTERN;
  constructor(
    private customerService: CustomerService,
    private router: Router,
    private location: Location
  ) { }

  ngOnInit() {
  }

  // customer login
  customerLogins() {
     this.customerService.login(this.customerLoginInfo.email, this.customerLoginInfo.password)
       .subscribe(response => {
          if (response.status === 200) {
            this.location.back();
          }
       });
  }
}
