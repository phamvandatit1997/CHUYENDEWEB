import {Component, OnInit} from '@angular/core';
import {UserService} from '../../../service/user.service';
import {User} from '../../../model/user';
import {TranslateService} from '../../../service/translate.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit {

  pagingRequest: any = {
    searchKey: '',
    sortCase: 1,
    ascSort: false,
    pageNumber: 1,
    pageSize: 10
  };

  users: Array<User> = [];
  collectionSize: number;

  constructor(
    private userService: UserService,
    private translate: TranslateService,
    private router: Router
  ) {
  }

  ngOnInit() {
    this.getListUser();
  }


  // get list user
  getListUser() {
    this.userService.getListUser(this.pagingRequest)
      .subscribe(response => {
        this.users = response.content;
        this.collectionSize = response.totalElements;
      });
  }

  changeStatusUser(id) {
    this.userService.changeStatusUser(id).subscribe(res => {
      if (res.status === 200) {
        this.getListUser();
        this.router.navigateByUrl('/admin/user/list');
      }
    });
  }
}
