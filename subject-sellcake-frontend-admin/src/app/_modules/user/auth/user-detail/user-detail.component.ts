import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {UserService} from '../../../service/user.service';
import {TranslateService} from '../../../service/translate.service';

@Component({
  selector: 'app-user-detail',
  templateUrl: './user-detail.component.html',
  styleUrls: ['./user-detail.component.css']
})
export class UserDetailComponent implements OnInit {
  id: string;
  imgUrl: string = null;
  currentUser: any = {};

  constructor(
    private router: Router,
    private userService: UserService,
    private route: ActivatedRoute,
    private translate: TranslateService
  ) {
  }

  ngOnInit() {
    this.route.params.subscribe(params => {
      this.id = params['id'];
      this.userService.getUserDetail(this.id).subscribe(user => {
        this.currentUser = user;
        this.imgUrl = user.images;
      });
    });
  }

}
