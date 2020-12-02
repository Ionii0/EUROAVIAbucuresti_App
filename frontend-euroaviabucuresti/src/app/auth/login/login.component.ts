import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { AuthService } from '../shared/auth.service';
import { LoginRequestPayload } from './login-request.payload';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  
  loginForm:FormGroup;
  loginRequestPayload:LoginRequestPayload;
  isError:boolean;
  
  constructor(private authService:AuthService, private router: Router, private toastr: ToastrService ) { 
    this.loginRequestPayload = {
      mailEuroavia:'',
      password:''
    }
  }

  ngOnInit(): void {
   this.loginForm = new FormGroup({
    mailEuroavia: new FormControl('', Validators.required),
    password: new FormControl('', Validators.required)
   })
  }

  login(): void {
    this.loginRequestPayload.mailEuroavia=this.loginForm.get('mailEuroavia').value;
    this.loginRequestPayload.password=this.loginForm.get('password').value;

    this.authService.login(this.loginRequestPayload).
      subscribe(data=>{
      if(data){
        this.isError=false;
        this.router.navigateByUrl('/');
        this.toastr.success('Login Successful');
      } else{
        this.isError=true;
      }
    });
  }

}
