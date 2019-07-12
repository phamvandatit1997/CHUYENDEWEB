import { Component, OnInit } from '@angular/core';
import {CustomerService} from '../../../service/customer.service';
import {Router} from '@angular/router';
import {CustomToastrService} from '../../../service/custom-toastr.service';

@Component({
  selector: 'app-customer-register',
  templateUrl: './customer-register.component.html',
  styleUrls: ['./customer-register.component.css']
})
export class CustomerRegisterComponent implements OnInit {

  customerRegisterInfo: any = {
      firstName:'',
      lastName:'',
      gender:'',
      birthday:'',
      email:'',
      ward:'',
      district:'',
      city:'',
      phoneNumber:'',
      password: ''
  };

  constructor(
    private customerService: CustomerService,
    private router: Router,
    private customToastrService: CustomToastrService
  ) { }

  ngOnInit() {}

  customerRegister() {
    console.log(this.customerRegisterInfo);
      this.customerService.createCustomer(this.customerRegisterInfo)
        .subscribe(response => {
            if (response.status == 200){
                this.router.navigate(['/cake/customer/login']);
            }
        });
  }
}
