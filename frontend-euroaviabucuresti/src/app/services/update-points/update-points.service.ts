import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class UpdatePointsService {

  constructor(private httpClient: HttpClient) {
  }

  updatePoints(userId: number, nrOfPoints: number): Observable<any> {
    return this.httpClient.get('http://localhost:8080/api/admin/modifyPoints/' + userId + '/' + nrOfPoints);
  }
}
