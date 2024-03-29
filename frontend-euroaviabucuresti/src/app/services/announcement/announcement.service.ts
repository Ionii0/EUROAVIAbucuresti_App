import { Injectable } from '@angular/core';
import {AnnouncementCreatePayload} from "./announcement-create-payload.payload";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {AnnouncementModel} from "./announcement-model";

@Injectable({
  providedIn: 'root'
})
export class AnnouncementService {

  constructor(private http: HttpClient) {
  }

  getAllAnnouncements(): Observable<Array<AnnouncementModel>> {
    return this.http.get<Array<AnnouncementModel>>('http://localhost:8080/api/announcement');
  }

  createAnnouncement(announcementCreatePayload: AnnouncementCreatePayload): Observable<any> {
    return this.http.post('http://localhost:8080/api/announcement/', announcementCreatePayload);
  }

  getAnnouncement(id: number): Observable<AnnouncementModel> {
    return this.http.get<AnnouncementModel>('http://localhost:8080/api/announcement/' + id);
  }

}
