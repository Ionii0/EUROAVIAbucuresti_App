import { Component, OnInit } from '@angular/core';
import { AnnouncementModel } from '../services/announcement-model';
import { AnnouncementService } from '../services/announcement.service';
import {faComments, faEye, faGrinTongueSquint, faPlane, faPlaneArrival} from '@fortawesome/free-solid-svg-icons';
import { Router, UrlTree } from '@angular/router';
import { ParticipateService } from '../services/participate/participate.service';
import { ParticipatePayload } from '../services/participate/participate-payload.payload';
import { AuthService } from '../auth/shared/auth.service';
import { map } from 'rxjs/internal/operators/map';

@Component({
  selector: 'app-announcements',
  templateUrl: './announcements.component.html',
  styleUrls: ['./announcements.component.css']
})
export class AnnouncementsComponent implements OnInit {
  announcements: Array<AnnouncementModel>=[];
  faComments=faComments;
  faPlane=faPlane;
  faPlaneArrival=faPlaneArrival;
  faEye=faEye;
  viewAll:boolean;
  participatePayload:ParticipatePayload;
  

  constructor(private announcementService:AnnouncementService, private router: Router, private participateService:ParticipateService, private authService:AuthService) { 
    this.announcementService.getAllAnnouncements().subscribe(announcement=>{
      this.announcements=announcement;
      this.announcements.forEach(element => {
        this.isParticipantAlready(element.id,element).subscribe();
        console.log(element.isParticipant);
      });
    });
   
 
  
  
     
  }

  ngOnInit(): void {

  }

  goToAnnouncement(id: number): void {
    this.router.navigateByUrl('/view-announcement/' + id);
  }

  participateOrWithdraw(id:number):void{
    this.participatePayload={
      announcementId:id,
      mailEuroavia:this.authService.getMailEuroavia()
    }
    console.log(id);
    this.participateService.participateOrWithdraw(this.participatePayload).subscribe(data=>{console.log(data);});
    location.reload();
  }
  isParticipantAlready(announcementId:number,announcement:AnnouncementModel):any{
  
    return this.participateService.isParticipantAlready(announcementId).pipe(map(
      data=>{
        console.log(data);
        if(data==true)
        announcement.isParticipant=true;
        else announcement.isParticipant=false;
        console.log(this.viewAll);
      }, error=>{
        console.log(error);
      }
    ));
      
  }

}
