import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {CalendarModel} from "./calendar-model";
import {AnnouncementModel} from "../announcement/announcement-model";

@Injectable({
  providedIn: 'root'
})
export class CalendarService {

  constructor(private http: HttpClient) {
  }

  public addEvent(title:string,startDate: string,endDate:string,allDay:boolean):Observable<any>{
    console.log("Sent event: " + title + " " + startDate +" "+allDay);
    return this.http.get("http://localhost:8080/api/fullcalendar/addEvent/"
                          +title+"/"+startDate+"/"+endDate+"/"+allDay);
  }

  public retrieveEvents():Observable<Array<CalendarModel>>{
    return this.http.get<Array<CalendarModel>>("http://localhost:8080/api/fullcalendar/retrieveEvents/");
  }

  public removeEvent(id:string):Observable<any>{
    return this.http.get("http://localhost:8080/api/fullcalendar/removeEvent/"+id);
  }
}
