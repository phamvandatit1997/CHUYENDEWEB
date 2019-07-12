import { Injectable } from '@angular/core';
import {ApiService} from './api.service';
import {API} from '../../_util/constant/api.constant';
import {map} from 'rxjs/operators';
import {Observable} from 'rxjs';
import {CustomToastrService} from './custom-toastr.service';
import {Product} from '../model/product';
@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(
    private apiService: ApiService,
    private toastrService: CustomToastrService
  ) { }
  // get list product
  getListProduct(pagingModel: Object): Observable<any> {
      return this.apiService.post(API.PRODUCT_LIST, pagingModel)
        .pipe(map(res => res.data));
  }

  //
  getProduct(id: string): Observable<Product> {
    return this.apiService.get(API.PRODUCT_DETAIL + id)
      .pipe(map(res => {
        // tslint:disable-next-line:triple-equals
        if (res.status == 200) {
          return res.data;
        } else {
          return null;
        }
      }));
  }

  // create product
  createProduct(product: any) {
      const productRequest = {
        productName: product.productName,
        productTypeId: product.productTypeId,
        description: product.description,
        unitPrice: product.unitPrice,
        promotionPrice: product.promotionPrice,
        unit: product.unit,
        images: product.image
      };
      const formData = new FormData();
      formData.append('images', product.imgFile);
      formData.append('product', new Blob([JSON.stringify(productRequest)], {type: 'application/json'}));
      return this.apiService.formData(API.PRODUCT_CREATE, formData)
        .pipe(map(res => res));
  }

  // delete product
  changeStatusProduct(id: string): Observable<any> {
    return this.apiService.put(API.CHANGE_PRODUCT_STATUS + '?product_id=' + id).
    pipe(map(res => {
      // tslint:disable-next-line:triple-equals
      if ( res.status == 200) {
        this.toastrService.showSuccessToastr('change_status');
        return res;
      } else {
        this.toastrService.showErrorAPI(res.status);
      }
    }));
  }

  // update product
  updateProduct(product: any) {
    const productRequest = {
      productId : product.productId,
      productName : product.productName,
      productTypeId: product.productTypeId,
      description : product.description,
      unitPrice : product.unitPrice,
      unit: product.unit,
      promotionPrice: product.promotionPrice,
      images: product.image
    };
    const formData = new FormData();
    formData.append('images', product.imgFile);
    formData.append('product_id', product.productId);
    formData.append('product_request', new Blob([JSON.stringify(productRequest)], {type: 'application/json'}));
    return this.apiService.putFormData(API.PRODUCT_UPDATE, formData)
      .pipe(map(res => {
        if (res.status === 200) {
          this.toastrService.showSuccessToastr('product.update_successfully');
          return res.data;
        } else {
          return null;
        }
      }));
  }
}
