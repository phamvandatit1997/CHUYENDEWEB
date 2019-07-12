import { Component, OnInit } from '@angular/core';
import {AuthBaseService} from '../../../service/auth-base.service';
import {TranslateService} from '../../../service/translate.service';
import {Pattern} from '../../../../_util/constant/pattern.constant';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
    loading = false;
    url = '/admin/dasboard';
    userLogin: any = {
      email: '',
      password: ''
    };
    emailPattent: any = Pattern.EMAIL_PATTERN;

  constructor(
    private authUserService: AuthBaseService,
    private translate: TranslateService
  ) { }
  ngOnInit() {}

  // login user by username and password
  loginUser() {
      this.authUserService.login(this.userLogin.email, this.userLogin.password, this.url);
  }
}
