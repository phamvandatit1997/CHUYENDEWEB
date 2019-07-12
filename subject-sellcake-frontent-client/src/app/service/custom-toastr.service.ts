import {Injectable, OnInit} from '@angular/core';
import {ToastrService} from 'ngx-toastr';
import {TranslateService} from './translate.service';
import {Router} from '@angular/router';
@Injectable({
  providedIn: 'root'
})
export class CustomToastrService implements OnInit{

  lang: string = "";

  constructor(
    private toastrService: ToastrService,
    private translateService: TranslateService,
    private router: Router
  ) { }

  ngOnInit(): void {
  }

  // show success toastr
  showSuccessToastr(key: string){
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
    return key.split('.').reduce((prev, current)=>{
      return prev[current];
    }, this.translateService.data);
  }
}
