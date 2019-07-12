import { Injectable } from '@angular/core';
import {ApiService} from './api.service';
import {Observable} from 'rxjs';
import {API} from '../util/constant/api.constant';
import {map} from 'rxjs/operators';
import {Product} from '../modules/models/product';
import {CustomToastrService} from './custom-toastr.service';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(
    private apiService: ApiService,
    private customerToastrService: CustomToastrService
  ) { }
  // get list product paging
  getListProductPaging(pagingModel: Object): Observable<any> {
    return this.apiService.post(API.PRODUCT_LISTS, pagingModel)
      .pipe(map(res => res.data));
  }

  // get product by product type id
  getProductByProductTypeId(id: string): Observable<any> {
    return this.apiService.get(API.PRODUCT_BY_PRODUCTTYPEID + id)
      .pipe(map(res => {
          if (res.status == 200){
            return res.data;
          } else {
            return null;
          }
      }))
  }
  // get product by id
  getProductById(id: string):Observable<Product> {
      return this.apiService.get(API.PRODUCT_DETAIL + id)
        .pipe(map(res => {
            if (res.status === 200) {
               return res.data;
            } else {
               return null;
            }
        }));
  }
}
