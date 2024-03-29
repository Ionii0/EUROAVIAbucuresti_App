import { Injectable } from '@angular/core';
import {map, tap} from "rxjs/operators";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import { LocalStorageService } from 'ngx-webstorage';
import {RegisterRequestPayload} from "../signup/register-request.payload";
import {LoginRequestPayload} from "../login/login-request.payload";
import {AuthenticationResponse} from "../login/authentication-response.payload";

@Injectable({
  providedIn: 'root'
})
export class AuthService {


  constructor(private httpClient:HttpClient , private localStorage:LocalStorageService) { }

  logout() {
    this.localStorage.clear('refreshToken');
    this.localStorage.clear('mailEuroavia');
    this.localStorage.clear('authenticationToken');
    this.localStorage.clear('expiresAt');
  }

  isAuthenticated():Boolean{
    return this.localStorage.retrieve('mailEuroavia')!=null;
  }

  isAdmin(): Observable<any>{
    return this.httpClient.post('http://localhost:8080/api/roleCheck', {responseType:'text'});
  }

  refreshTokenPayload = {
    refreshToken: this.getRefreshToken(),
    mailEuroavia: this.getMailEuroavia()
  }

  getMailEuroavia() {
    return this.localStorage.retrieve('mailEuroavia');
  }

  getRefreshToken() {
    return this.localStorage.retrieve('refreshToken');
  }

  getJwtToken() {
    return this.localStorage.retrieve('authenticationToken');
  }


  signup(registerRequestPayload:RegisterRequestPayload) :Observable<any>{
    return this.httpClient.post('http://localhost:8080/api/auth/signup', registerRequestPayload, {responseType:'text'});
  }

  login(loginRequestPayload: LoginRequestPayload): Observable<boolean> {
    return this.httpClient.post<AuthenticationResponse>('http://localhost:8080/api/auth/login', loginRequestPayload).
    pipe(map(data=>{
      this.localStorage.store('authenticationToken', data.authenticationToken);
      this.localStorage.store('mailEuroavia', data.mailEuroavia);
      this.localStorage.store('refreshToken', data.refreshToken);
      this.localStorage.store('expiresAt', data.expiresAt);

      return true;
    }));
  }
  refreshToken() {
    return this.httpClient.post<AuthenticationResponse>('http://localhost:8080/api/auth/refreshToken',
      this.refreshTokenPayload)
      .pipe(tap(response => {
        this.localStorage.clear('authenticationToken');
        this.localStorage.clear('expiresAt');

        this.localStorage.store('authenticationToken',
          response.authenticationToken);
        this.localStorage.store('expiresAt', response.expiresAt);
      }));
  }

}
