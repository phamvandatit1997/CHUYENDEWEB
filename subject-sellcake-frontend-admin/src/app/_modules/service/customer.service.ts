import {Injectable} from '@angular/core';
import {ApiService} from './api.service';
import {Observable} from 'rxjs';
import {API} from '../../_util/constant/api.constant';
import {map} from 'rxjs/operators';
import {CustomToastrService} from './custom-toastr.service';
import {Customers} from '../model/customers';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  constructor(
    private apiService: ApiService,
    private toastrService: CustomToastrService
  ) {
  }

  // get list-product-type customer paging and sort search
  getListCustomer(pagingModel: Object): Observable<any> {
    return this.apiService.post(API.CUSTOMER_LIST, pagingModel)
      .pipe(map(res => {
        if (res.status == 200) {
          return res.data;
        } else {
          this.toastrService.showErrorAPI(res.status);
        }
      }));
  }

  // get one customer
  getCustomer(id: string): Observable<Customers> {
    return this.apiService.get(API.GET_CUSTOMER + id)
      .pipe(map(res => {
        if (res.status === 200) {
          return res.data;
        } else {
          this.toastrService.showErrorAPI(res.status);
        }
      }));
  }

  // change status customer
  changeStatusCustomer(id: string): Observable<any> {
    return this.apiService.put(API.CHANGE_CUSTOMER_STATUS + '?customer_id=' + id)
      .pipe(map(res => {
        if (res.status == 200) {
          this.toastrService.showSuccessToastr('change_status');
          return res;
        } else {
          this.toastrService.showErrorAPI(res.status);
        }
      }));
  }
}
