import {Injectable} from '@angular/core';
import {ApiService} from './api.service';
import {Observable} from 'rxjs';
import {map} from 'rxjs/operators';
import {API} from '../../_util/constant/api.constant';
import {CustomToastrService} from './custom-toastr.service';

@Injectable({
  providedIn: 'root'
})
export class BillDetailService {

  constructor(
    private apiService: ApiService,
    private toastrService: CustomToastrService
  ) {
  }

  // get bill detail
  billDetail(id: string): Observable<any> {
    return this.apiService.get(API.BILL_DETAIL + '?billId=' + id)
      .pipe(map(res => {
        if (res.status === 200) {
          return res.data;
        } else {
          this.toastrService.showErrorAPI(res.status);
        }
      }));
  }
}
