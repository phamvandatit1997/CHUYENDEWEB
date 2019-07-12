import { Injectable } from '@angular/core';
import {ApiService} from './api.service';
import {API} from '../util/constant/api.constant';
import {map} from 'rxjs/operators';
import {SecurityService} from './security.service';
import {SessionUserService} from './session-user.service';
import {Router} from '@angular/router';
import {Observable} from 'rxjs';
import {Customer} from '../modules/models/customer';
import {CustomToastrService} from './custom-toastr.service';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  constructor(
    private apiService: ApiService,
    private securityService: SecurityService,
    private sessionService: SessionUserService,
    private router: Router,
    private customerToastrService: CustomToastrService
  ) {}

  // create customer
  createCustomer(customer: any){
      return this.apiService.post(API.CUSTOMER_CREATE, customer)
        .pipe(map(res => res));
  }
  // customer login
  login(email: string, password: string): Observable<any> {
    return this.apiService.post(API.CUSTOMER_LOGIN, {email: email, password:this.securityService.MD5Hash(password)})
      .pipe(map(res => {
          if (res.status === 200) {
              this.sessionService.setAccessToken(res.data);
              this.customerToastrService.showSuccessToastr('login.success');
              return res;
          } else {
              return null;
          }
      }))
  }

  // logout
  logout() {
      this.apiService.get(API.CUSTOMER_LOGOUT)
        .pipe(res => res).subscribe(response => {
        this.sessionService.destroyAccessToken();
      });
  }

  // detail customer by token
  detailCustomerByToken(): Observable<Customer> {
      return this.apiService.get(API.DETAIL_CUSTOMER_BY_TOKEN)
        .pipe(map(res => {
            if (res.status === 200) {
               return res.data;
            } else {
               return null;
            }
        }));
  }
}
