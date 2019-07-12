import { Injectable } from '@angular/core';
import {ApiService} from './api.service';
import {Observable} from 'rxjs';
import {API} from '../../_util/constant/api.constant';
import {map} from 'rxjs/operators';
import {ProductTypes} from '../model/product-types';
import {CustomToastrService} from './custom-toastr.service';
@Injectable()
export class ProductTypeService {

  constructor(
    private apiService: ApiService,
    private toastrService: CustomToastrService
  ) { }

  // get list-product-type product type
  getListProductType(pagingModel: Object): Observable<any> {
    return this.apiService.post(API.PRODUCT_TYPE_LIST, pagingModel)
      .pipe(map(res => res.data));
  }

  // create-product-type product type
  createProductType(productType: any) {
      const productTypeRequest = {
        productTypeName: productType.productTypeName,
        productTypeDescription: productType.productTypeDescription,
        images: productType.image
      };

      const formData: FormData = new FormData();
      formData.append('images', productType.imgFile);
      formData.append('product_types_request', new Blob([JSON.stringify(productTypeRequest)], {type: 'application/json'}));

      return this.apiService.formData(API.PRODUCT_TYPE_CREATE, formData)
        .pipe(map(res => res));
  }
  // change status product type
  changeStatusProductType(id: string): Observable<any> {
    return this.apiService.put(API.CHANGE_PRODUCT_TYPE_STATUS + '?product_type_id=' + id)
      .pipe(map(res => {
        // tslint:disable-next-line:triple-equals
          if (res.status == 200) {
            this.toastrService.showSuccessToastr('change_status');
            return res;
          } else {
            this.toastrService.showErrorAPI(res.status);
          }
      }));
  }
  // get product type one
  getProductType(id: string): Observable<ProductTypes> {
    return this.apiService.get(API.PRODUCT_TYPE_DETAIL + id)
      .pipe(map(res => {
        // tslint:disable-next-line:triple-equals
          if (res.status == 200) {
            return res.data;
          } else {
            return null;
          }
      }));
  }
  // update product type
  update(productTyte: ProductTypes): Observable<ProductTypes> {
      const productTypeRequest = {
        productTyteId: productTyte.productTypeId,
        productTypeName: productTyte.productTypeName,
        productTypeDescription: productTyte.productTypeDescription,
        productTypeImage: productTyte.productTypeImage,
      };
      const formatData = new FormData();
      formatData.append('images', productTyte.imageFile);
      console.log(productTyte.imageFile);
      formatData.append('productTypeId', productTyte.productTypeId);
      formatData.append('product_types_request', new Blob([JSON.stringify(productTypeRequest)], {type: 'application/json'}));
      return this.apiService.putFormData(API.PRODUCT_TYPE_UPDATE, formatData)
        .pipe(map(res => {
          // tslint:disable-next-line:triple-equals
            if (res.status == 200) {
              return res.data;
            } else {
              return null;
            }
        }));

  }
}
