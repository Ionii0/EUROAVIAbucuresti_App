import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class UpdateTokensService {

  constructor(private httpClient: HttpClient) {
  }

  updateTokens(userId: number, nrOfTokens: number): Observable<any> {
    return this.httpClient.get('http://localhost:8080/api/admin/modifyTokens/' + userId + '/' + nrOfTokens);
  }
}
