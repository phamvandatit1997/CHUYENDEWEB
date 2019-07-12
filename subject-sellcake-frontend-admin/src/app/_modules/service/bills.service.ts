import {Injectable} from '@angular/core';
import {ApiService} from './api.service';
import {Observable, pipe} from 'rxjs';
import {map} from 'rxjs/operators';
import {API} from '../../_util/constant/api.constant';
import {CustomToastrService} from './custom-toastr.service';
import {formatDate} from '@angular/common';
import {Bills} from '../model/bills';

@Injectable({
  providedIn: 'root'
})
export class BillsService {

  bill: Bills;

  constructor(
    private apiService: ApiService,
    private toastrService: CustomToastrService
  ) {
  }

  // get bill paging sort search
  getListBill(pagingModel: Object): Observable<any> {
    return this.apiService.post(API.BILL_LIST, pagingModel)
      .pipe(map(res => {
        if (res.status === 200) {
          return res.data;
        } else {
          this.toastrService.showErrorAPI(res.status);
        }
      }));
  }

  changeBillStatus(id: string): Observable<any> {
    return this.apiService.put(API.CHANGE_BILL_STATUS + '?billId=' + id).pipe(map(res => {
      if (res.status === 200) {
        this.toastrService.showSuccessToastr('change_status');
        return res;
      } else {
        this.toastrService.showErrorAPI(res.status);
      }
    }));
  }

  getBillsByPayment(payment: string): Observable<any> {
    return this.apiService.get(API.BILL_LIST_BY_PAYMENT + '?payment=' + payment).pipe(map(res => {
      if (res.status === 200) {
        return res;
      } else {
        this.toastrService.showErrorAPI(res.status);
      }
    }));
  }

  // export excel
  exportExcel(): Observable<any> {
    return this.apiService.get(API.BILL_EXPORT_TO_EXCEL).pipe(map(res => {
      if (res.status === 200) {
        this.toastrService.showSuccessToastr('export_success');
        return res;
      } else {
        this.toastrService.showErrorAPI(res.status);
      }
    }));
  }
}
