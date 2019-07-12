import {Injectable, OnInit} from '@angular/core';
import {ToastrService} from 'ngx-toastr';
import {TranslateService} from './translate.service';
import {Router} from '@angular/router';
import * as _ from 'lodash';
import {APIStatus} from '../../_util/constant/api-status.constant';
@Injectable({
  providedIn: 'root'
})
export class CustomToastrService implements OnInit {

  lang = '';

  constructor(
    private toastrService: ToastrService,
    private translateService: TranslateService,
    private router: Router
  ) { }

  ngOnInit(): void {
  }

  showErrorAPI(status: number) {
     const err = _.find(APIStatus, {status: status});
     if (err) {
        this.toastrService.error(err.message);
     }
  }
  // show success toastr
  showSuccessToastr(key: string) {
      this.toastrService.success('', this.translateContentToast(key));
  }

  // show error toastr
  showErrorToastr(key: string) {
      this.toastrService.error('', this.translateContentToast(key));
  }

  // show danger toastr
  showWarningToastr(key: string) {
      this.toastrService.warning('', this.translateContentToast(key));
  }
  translateContentToast(key) {
    return key.split('.').reduce((prev, current) => {
      return prev[current];
    }, this.translateService.data);
  }
}
