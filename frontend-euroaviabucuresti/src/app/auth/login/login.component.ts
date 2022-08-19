import {Component, OnInit} from '@angular/core';
import {LoginRequestPayload} from "./login-request.payload";
import {AuthService} from "../auth/auth.service";
import {Router} from "@angular/router";
import {ToastrService} from "ngx-toastr";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {

  loginRequestPayload: LoginRequestPayload;
  isError?: boolean;
  password: string = "";
  mailEuroavia: string = "";

  constructor(private authService: AuthService, private router: Router, private toastr:ToastrService) {
    this.loginRequestPayload = {
      mailEuroavia: '',
      password: ''
    }
  }

  ngOnInit(): void {
  }

  login(): void {
    this.loginRequestPayload.mailEuroavia = this.mailEuroavia;
    this.loginRequestPayload.password = this.password;

    this.authService.login(this.loginRequestPayload).subscribe(data => {
      if (data) {
        this.isError = false;
        this.router.navigateByUrl('/announcement-page').then(()=>{});
        this.toastr.success('Login Successful');
      } else {
        this.isError = true;
      }
    },
      (() => {
        this.toastr.error('Wrong Credentials');
      }))
  }


}
