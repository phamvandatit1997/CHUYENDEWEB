import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {UserService} from '../../../service/user.service';
import {CustomToastrService} from '../../../service/custom-toastr.service';
import {TranslateService} from '../../../service/translate.service';
import {Pattern} from '../../../../_util/constant/pattern.constant';

@Component({
  selector: 'app-user-create',
  templateUrl: './user-create.component.html',
  styleUrls: ['./user-create.component.css']
})
export class UserCreateComponent implements OnInit {

  userInfo: any = {
      userName: '',
      firstName: '',
      lastName: '',
      email: '',
      password: '',
      address: '',
      phone: '',
      lang: '',
      birthday: ''
  };

  imgUrl: string;

  constructor(
    private route: ActivatedRoute,
    private userService: UserService,
    private toastrService: CustomToastrService,
    private router: Router,
    private translate: TranslateService
  ) {
    this.route.params.subscribe(params => {
        this.userInfo.firstName = params['firstName'];
        this.userInfo.lastName = params['lastName'];
        this.userInfo.userName = params['userName'];
        this.userInfo.email = params['email'];
        this.userInfo.password = params['password'];
        this.userInfo.address = params['address'];
        this.userInfo.lang = params['lang'];
        this.userInfo.phone = params['phone'];
        this.userInfo.birthday = params['birthday'];
        this.userInfo.image = params['image'];
        this.imgUrl = params['image'] || '';
    });
  }

  emailPattern: any = Pattern.EMAIL_PATTERN;

  ngOnInit() {}

  // create user
  createUser() {
      this.userService.createUser(this.userInfo).subscribe(response => {
        if (response.status === 200) {
          this.toastrService.showSuccessToastr('user.successfuly');
          this.router.navigate(['/admin/user/list']);
        } else {
          this.toastrService.showErrorAPI(response.status);
        }
      });
  }

  // read url images
  readUrl(event: any) {
    if (event.target.files && event.target.files.item(0)) {
      this.userInfo.imgFile = event.target.files.item(0);
      const reader = new FileReader();
      // tslint:disable-next-line:no-shadowed-variable
      reader.onload = (event: ProgressEvent) => {
        // @ts-ignore
        this.imgUrl = (<FileReader>event.target).result;
      };
      reader.readAsDataURL(event.target.files[0]);
    }
  }
}
