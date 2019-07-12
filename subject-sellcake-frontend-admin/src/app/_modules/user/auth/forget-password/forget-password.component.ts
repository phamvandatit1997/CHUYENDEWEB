import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';

@Component({
  selector: 'app-forget-password',
  templateUrl: './forget-password.component.html',
  styleUrls: ['./forget-password.component.css']
})
export class ForgetPasswordComponent implements OnInit {

  // forgetpassword: FormGroup;
  // submitted: boolean = false;
  constructor(
    // private formBuilder: FormBuilder
  ) { }

  ngOnInit() {
    // this.forgetpassword = this.formBuilder.group({
    //   email: ['', Validators.email]
    // })
  }
  // get f() {
  //   return this.forgetpassword.controls;
  // }
  //
  // forgetPassword() {}
}
