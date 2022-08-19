import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {ManageUsersModel} from "./manage-users-model";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class ManageUsersService {

  constructor(private http: HttpClient) {}

  public getAllUsers(): Observable<Array<ManageUsersModel>> {
    return this.http.get<Array<ManageUsersModel>>('http://localhost:8080/api/admin/displayAllUsers');
  }

}
