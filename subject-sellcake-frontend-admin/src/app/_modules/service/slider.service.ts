import { Injectable } from '@angular/core';
import {ApiService} from './api.service';
import {Observable} from 'rxjs';
import {API} from '../../_util/constant/api.constant';
import {map} from 'rxjs/operators';
import {ToastrService} from 'ngx-toastr';
import {CustomToastrService} from './custom-toastr.service';
@Injectable({
  providedIn: 'root'
})
export class SliderService {

  constructor(
    private apiService: ApiService,
    private toastrService: CustomToastrService
  ) { }
  // get slider and paging sort search
  getListSlider(pagingModel: Object): Observable<any> {
      return this.apiService.post(API.SLIDER_LIST, pagingModel)
        .pipe(map(res => res.data));
  }
  // create slider
  createSlider(slider: any) {
    const sliderRequest = {
      images: slider.image
    };
    const fromData = new FormData();
    fromData.append('images', slider.imgFile);
    fromData.append('slider', new Blob([JSON.stringify(sliderRequest)], {type: 'application/json'}));

    return this.apiService.formData(API.SLIDER_CREATE, fromData)
      .pipe(map(res => res));
  }
  // change status
  changeStatusSlider(id: string): Observable<any> {
    return this.apiService.put(API.SLIDER_CHANGE_STATUS + '?slider_id=' + id)
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
}
