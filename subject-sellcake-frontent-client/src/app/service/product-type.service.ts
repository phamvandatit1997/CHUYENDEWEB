import { Injectable } from '@angular/core';
import {ApiService} from './api.service';
import {Observable} from 'rxjs';
import {API} from '../util/constant/api.constant';
import {map} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class ProductTypeService {

  constructor(
    private apiService: ApiService
  ) { }

  // get list product type
  getListProductType(): Observable<any> {
    return this.apiService.get(API.PRODUCT_TYPE_LIST)
      .pipe(map(res => {
          if (res.status == 200){
            return res.data;
          } else {
            return null;
          }
      }));
  }
}
