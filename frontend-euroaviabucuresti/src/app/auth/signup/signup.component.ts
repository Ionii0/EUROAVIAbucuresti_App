import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { AuthService } from '../shared/auth.service';
import { RegisterRequestPayload } from './register-request.payload';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  signupForm:FormGroup;
  registerRequestPayload:RegisterRequestPayload;

  constructor(private authService:AuthService, private toastr:ToastrService) { 
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
    this.signupForm= new FormGroup({
      emailEuroavia: new FormControl('', [Validators.required, Validators.email]),
      emailPersonal: new FormControl('', [Validators.required, Validators.email]),
      password: new FormControl('', Validators.required),
      department: new FormControl('', Validators.required),
      firstName: new FormControl('', Validators.required),
      lastName: new FormControl('', Validators.required)
    })
  }

  signup(): void {
    this.registerRequestPayload.emailEuroavia=this.signupForm.get('emailEuroavia').value;
    this.registerRequestPayload.emailPersonal=this.signupForm.get('emailPersonal').value;
    this.registerRequestPayload.password=this.signupForm.get('password').value;
    this.registerRequestPayload.department=this.signupForm.get('department').value;
    this.registerRequestPayload.firstName=this.signupForm.get('firstName').value;
    this.registerRequestPayload.lastName=this.signupForm.get('lastName').value;

    this.authService.signup(this.registerRequestPayload)
    .subscribe(()=>{
      this.toastr.success("Signup successful! Please check your inbox for activation!");
    }, ()=>{
      this.toastr.error('Registration failed!');  
    });
    
  }

}
