import { Injectable } from '@angular/core';
import {ApiService} from './api.service';
import {map} from 'rxjs/operators';
import {API} from '../util/constant/api.constant';
import {CartService} from './cart.service';
import {Router} from '@angular/router';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class BillService {

  constructor(
    private apiService: ApiService,
    private cartService: CartService,
    private router: Router
  ) { }

  // create bill
  createBill(billList: Object){
      this.apiService.post(API.BILL_CREATE, billList)
        .pipe(res => res).subscribe(response => {
            if (response.status === 200) {
                this.apiService.get(API.CART_DELETE_ALL_BY_CUSTOMERID)
                  .pipe(cus => cus).subscribe(cusResponse => {
                    if (cusResponse.status === 200) {
                        this.router.navigate(['/cake/home']);
                        location.reload(true);
                    }
                });
            }
        });
  }
  // delete all product in cart by customer id
  deleteAllProductInCartByCustomerId() {
    return this.apiService.get(API.CART_DELETE_ALL_BY_CUSTOMERID)
      .pipe(map(response => {
        if (response.status === 200) {
          console.log(response.status);
        }
      }));
  }
  // bill detail by customer id
  billDetailByCustomerId(): Observable<any> {
      return this.apiService.get(API.BILL_DETAIL_BY_CUSTOMER)
        .pipe(map(res => {
            if (res.status === 200) {
              return res.data;
            } else {
              return null;
            }
        }))
  }
}
