import { Injectable } from '@angular/core';
import {ApiService} from './api.service';
import {Observable} from 'rxjs';
import {API} from '../util/constant/api.constant';
import {map} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class SliderService {

  constructor(
    private apiService: ApiService
  ) { }
  // get list slider
  showListSlider(pagingModel: Object): Observable<any> {
      return this.apiService.post(API.SLIDER_LIST, pagingModel)
        .pipe(map(res => {
            if (res.status == 200) {
              return res.data;
            } else {
              return null;
            }
        }));
  }
}
