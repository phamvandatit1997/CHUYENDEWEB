import { Injectable } from '@angular/core';
import {CookieService} from 'ngx-cookie-service';
import {AppConfig} from '../../_util/constant/config.constant';

@Injectable({
  providedIn: 'root'
})
export class SessionUserService {
  private sessionUser: string;
  constructor(private cookieService: CookieService) { }

  // set access token
  setAccessToken(token) {
    if (token != null) {
      this.cookieService.set(AppConfig.SESSION_COOKIES, token, AppConfig.TOKEN_EXPIRE_DATE);
    }
  }
  // get access token
  getAccessToken() {
    return this.cookieService.get(AppConfig.SESSION_COOKIES);
  }
  // destroy accessToken
  destroyAccessToken() {
    this.cookieService.delete(AppConfig.SESSION_COOKIES);
  }
  // get user
  getUser() {
    return this.sessionUser;
  }
  // set user
  setUser(user) {
    if (user != null && typeof user === 'object') {
      this.sessionUser = user;
    }
  }
  detectBrowserLang() {
    const lang = window.navigator.language;
    const language = lang.substr(0, 2);
    switch (language) {
      case 'en':
      case 'vi':
        return language;
      default:
        return 'en';
    }
  }

}
