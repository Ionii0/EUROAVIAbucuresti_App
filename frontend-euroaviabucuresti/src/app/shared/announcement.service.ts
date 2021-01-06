import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AnnouncementModel } from './announcement-model';

@Injectable({
  providedIn: 'root'
})
export class AnnouncementService {

  constructor(private http: HttpClient) { }

  getAllAnnouncements():Observable<Array<AnnouncementModel>>{
    return this.http.get<Array<AnnouncementModel>>('http://localhost:8080/api/announcement')
  }

}
