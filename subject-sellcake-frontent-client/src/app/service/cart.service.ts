import { Injectable } from '@angular/core';
import {ApiService} from './api.service';
import {Observable} from 'rxjs';
import {API} from '../util/constant/api.constant';
import {map} from 'rxjs/operators';
import {CustomToastrService} from './custom-toastr.service';

@Injectable({
  providedIn: 'root'
})
export class CartService {

  constructor(
    private apiService: ApiService,
    private customerToastrService: CustomToastrService
  ) { }

  // add cart
  addCart(id: string) {
    this.apiService.get(API.CART_ADD + "?product_id=" + id)
      .pipe(res => res).subscribe(response => {
          if (response.status === 200) {
              location.reload();
          } else {
            this.customerToastrService.showErrorToastr('login.no_success');
          }
      });
  }
  // get list cart
  getListCart(): Observable<any> {
      return this.apiService.get(API.CART_LIST)
        .pipe(map(res => {
            if (res.status === 200) {
               return res.data;
            } else {
               return null;
            }
        }));
  }
  // delete one product in cart
  deleteOneProductInCart(id: string):Observable<any> {
      return this.apiService.get(API.CART_DELETE_ONE + id)
        .pipe(map(res => {
            if (res.status === 200) {
              location.reload();
              return res.data;
            } else {
              return null;
            }
        }));
  }
  // delete all Product in cart
  deleteAllProductInCart(id: string): Observable<any> {
      return this.apiService.get(API.CART_DELETE_ALL + id)
        .pipe(map(res => {
            if (res.status === 200) {
              location.reload();
              return res.data;
            } else {
              return null;
            }
        }));
  }
}
