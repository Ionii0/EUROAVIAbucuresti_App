import {Component, OnInit} from '@angular/core';
import {ToastrService} from "ngx-toastr";
import {AuthService} from "../auth/auth.service";
import {RegisterRequestPayload} from "./register-request.payload";
import {Router} from "@angular/router";

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {
  emailEuroavia: string = "";
  emailPersonal: string = "";
  firstName: string = "";
  lastName: string = "";
  department: string = "";
  password: string = "";
  passwordConfirm: string = "";
  registerRequestPayload:RegisterRequestPayload;


  constructor(private authService: AuthService, private toastr: ToastrService, private router:Router) {
    this.registerRequestPayload={
      emailPersonal: '',
      password: '',
      emailEuroavia: '',
      department: '',
      firstName: '',
      lastName: ''
    }
  }

  ngOnInit(): void {
  }
  signup(): void {

    this.registerRequestPayload.firstName=this.firstName;
    this.registerRequestPayload.lastName=this.lastName;
    this.registerRequestPayload.department=this.department;
    this.registerRequestPayload.emailPersonal=this.emailPersonal;
    this.registerRequestPayload.emailEuroavia=this.emailEuroavia;
    this.registerRequestPayload.password=this.passwordConfirm;

    this.authService.signup(this.registerRequestPayload)
      .subscribe(()=>{
        this.router.navigateByUrl('/login').then(()=>{});
        this.toastr.success("Signup successful! Please check your inbox for activation!");
      }, ()=>{
        this.toastr.error('Registration failed!');
      });

  }
}
