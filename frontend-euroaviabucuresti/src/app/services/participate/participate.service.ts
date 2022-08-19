import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {ParticipatePayload} from "./participate-payload.payload";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class ParticipateService {

  constructor(private httpClient: HttpClient) {
  }

  participateOrWithdraw(participatePayload: ParticipatePayload): Observable<any> {
    console.log(participatePayload);
    return this.httpClient.post('http://localhost:8080/api/participant/participateOrWithdraw/', participatePayload);

  }

  countParticipants(announcementId:number): Observable<any> {
    return this.httpClient.get('http://localhost:8080/api/participant/countParticipantsForAnnouncement/' + announcementId);
  }

  isParticipantAlready(announcementId:number): any {
    return this.httpClient.post('http://localhost:8080/api/participant/participationStatus/' + announcementId, {responseType: 'text'});
  }
}
