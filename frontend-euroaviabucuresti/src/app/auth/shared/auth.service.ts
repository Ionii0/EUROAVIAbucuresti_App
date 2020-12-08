import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import { RegisterRequestPayload } from '../signup/register-request.payload';
import { Observable } from 'rxjs';
import { LoginRequestPayload } from '../login/login-request.payload';
import { AuthenticationResponse } from '../login/authentication-response.payload';
import { LocalStorageService } from 'ngx-webstorage';
import {map, tap} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private httpClient:HttpClient , private localStorage:LocalStorageService) { }

  signup(registerRequestPayload:RegisterRequestPayload) :Observable<any>{
   return this.httpClient.post('http://localhost:8080/api/auth/signup', registerRequestPayload, {responseType:'text'});
  }

  login(loginRequestPayload: LoginRequestPayload): Observable<boolean> {
   return this.httpClient.post<AuthenticationResponse>('http://localhost:8080/api/auth/signup', loginRequestPayload).
   pipe(map(data=>{
      this.localStorage.store('authenticationToken', data.authenticationToken);
      this.localStorage.store('mailEuroavia', data.mailEuroavia);
      this.localStorage.store('refreshToken', data.refreshToken);
      this.localStorage.store('expiresAt', data.expiresAt);

      return true;
    }));
  }
}
