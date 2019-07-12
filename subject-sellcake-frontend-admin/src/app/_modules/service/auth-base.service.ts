import { Injectable } from '@angular/core';
import {ApiService} from './api.service';
import {SecurityService} from './security.service';
import {SessionUserService} from './session-user.service';
import {UserService} from './user.service';
import {Router} from '@angular/router';
import {API} from '../../_util/constant/api.constant';
import {CustomToastrService} from './custom-toastr.service';

@Injectable({
  providedIn: 'root'
})
export class AuthBaseService {

  constructor(
    private apiService: ApiService,
    private securityService: SecurityService,
    private sessionUserService: SessionUserService,
    private userService: UserService,
    private router: Router,
    private toastrService: CustomToastrService
  ) { }

  login(email: string, password: string, url: string) {
      this.apiService.post(API.LOGIN, {
        email: email,
        password: this.securityService.MD5Hash(password)
      }).pipe(res => res).subscribe(response => {
          if (response.status === 200) {
            this.sessionUserService.setAccessToken(response.data);
            this.toastrService.showSuccessToastr('login_successfully');
            this.router.navigate([url]);
          } else {
            this.toastrService.showErrorAPI(response.status);
          }
      });
  }
  logoutUser() {
    this.sessionUserService.destroyAccessToken();
    this.router.navigate(['/user/login']);
  }
}
