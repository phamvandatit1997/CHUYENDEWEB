import {Injectable} from '@angular/core';
import {ApiService} from './api.service';
import {Observable} from 'rxjs';
import {User} from '../model/user';
import {API} from '../../_util/constant/api.constant';
import {map} from 'rxjs/operators';
import {CustomToastrService} from './custom-toastr.service';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  constructor(
    private apiSerive: ApiService,
    private toastrService: CustomToastrService
  ) {
  }

  // get user detail logined
  getUserByToken(): Observable<User> {
    return this.apiSerive.get(API.USER_DETAIL_LOGINED)
      .pipe(map(res => {
        // tslint:disable-next-line:triple-equals
        if (res.status == 200) {
          return res.data;
        } else {
          return null;
        }
      }));
  }

  // get user detail
  getUserDetail(id: string): Observable<any> {
    return this.apiSerive.get(API.USER_DETAIL + id).pipe(map(res => {
      if (res.status === 200) {
        return res.data;
      } else {
        this.toastrService.showErrorAPI(res.status);
      }
    }));
  }

  // paging list user
  getListUser(pagingModel: Object): Observable<any> {
    return this.apiSerive.post(API.USER_LIST, pagingModel)
      .pipe(map(res => {
        if (res.status === 200) {
          return res.data;
        } else {
          return null;
        }
      }));
  }

  // create user
  createUser(user: any) {
    const userRequest = {
      userName: user.userName,
      firstName: user.firstName,
      lastName: user.lastName,
      email: user.email,
      password: user.password,
      address: user.address,
      phone: user.phone,
      lang: user.lang,
      birthday: user.birthday,
      images: user.image
    };

    const formData: FormData = new FormData();
    formData.append('images', user.imgFile);
    formData.append('user', new Blob([JSON.stringify(userRequest)], {type: 'application/json'}));

    return this.apiSerive.formData(API.USER_CREATE, formData)
      .pipe(map(res => res));
  }

  // change status user
  changeStatusUser(id: string): Observable<any> {
    return this.apiSerive.put(API.CHANGE_STATUS_USER + '?userId=' + id).pipe(map(res => {
      if (res.status === 200) {
        this.toastrService.showSuccessToastr('change_status');
        return res;
      } else {
        this.toastrService.showErrorAPI(res.status);
      }
    }));
  }
  // change lang user
  changeLangUser(lang: string): Observable<User> {
    return this.apiSerive.get(API.CHANGE_LANG_USER + '?lang=' + lang)
      .pipe(map(res => {
          if (res.status === 200) {
            this.toastrService.showSuccessToastr('change_lang');
            return res.data;
          } else {
            this.toastrService.showErrorAPI(res.status);
          }
      }));
  }
}
