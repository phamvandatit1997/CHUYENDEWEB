import {Component, OnInit} from '@angular/core';
import {TranslateService} from '../../_modules/service/translate.service';
import {UserService} from '../../_modules/service/user.service';
import {User} from '../../_modules/model/user';
import {AuthBaseService} from '../../_modules/service/auth-base.service';

@Component({
  selector: 'app-layout-container',
  templateUrl: './layout-container.component.html',
  styleUrls: ['./layout-container.component.css']
})
export class LayoutContainerComponent implements OnInit {

  user: User;
  role: string;
  images: string;
  fullName: string;
  birthday: string;
  createDate: string;
  email: string;
  address: string;
  phone: string;
  constructor(
    private translate: TranslateService,
    private userService: UserService,
    private authenService: AuthBaseService,
  ) {}

  ngOnInit(): void {
    this.userService.getUserByToken().subscribe(response => {
      this.user = response;
      if (this.user == null) {
          this.role = '';
          this.images = '';
          this.fullName = '';
          this.birthday = '';
          this.createDate = '';
          this.email = '';
          this.address = '';
          this.phone = '';
      } else {
          this.role = response.role;
          this.images = response.images;
          this.fullName = response.fullName;
          this.birthday = response.birthday;
          this.createDate = response.createDate;
          this.email = response.email;
          this.address = response.address;
          this.phone  = response.phone;
      }
    });
  }

  // logout user
  logoutUser() {
    this.authenService.logoutUser();
  }
  // chang languar in user
  changeUserLanguage(lang: string) {
      this.userService.changeLangUser(lang).subscribe(response => {
        this.user = response;
        this.translate.use(lang);
      });
  }
}
