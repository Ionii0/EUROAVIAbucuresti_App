import {Component, Input, OnInit} from '@angular/core';
import { faPlane, faPlaneArrival} from '@fortawesome/free-solid-svg-icons';
import {AnnouncementModel} from "../../services/announcement/announcement-model";
import {Router} from "@angular/router";
import {AuthService} from "../../auth/auth/auth.service";
import {ParticipateService} from "../../services/participate/participate.service";
import {ParticipatePayload} from "../../services/participate/participate-payload.payload";
import {map} from "rxjs/operators";

@Component({
  selector: 'app-announcement-card',
  templateUrl: './announcement-card.component.html',
  styleUrls: ['./announcement-card.component.css']
})
export class AnnouncementCardComponent implements OnInit {
  @Input()
  announcementData!: AnnouncementModel;
  private participatePayload?: ParticipatePayload;
  // private viewAll?: boolean;
  faPlane = faPlane;
  faPlaneArrival = faPlaneArrival;

  constructor(private router: Router, private authService: AuthService, private participateService: ParticipateService) {

  }

  ngOnInit(): void {
    this.isParticipantAlready(this.announcementData?.id, this.announcementData).subscribe();
  }

  goToAnnouncement(id: number): void {
    this.router.navigateByUrl('/view-announcement/' + id).then(() => {
    });
  }

  participateOrWithdraw(id: number): void {
    this.participatePayload = {
      announcementId: id,
      mailEuroavia: this.authService.getMailEuroavia()
    };
    if(this.announcementData.isParticipant)
      this.announcementData.participantCount--;
    else this.announcementData.participantCount++;
    this.participateService.participateOrWithdraw(this.participatePayload).subscribe(() => {
      this.isParticipantAlready(this.announcementData?.id, this.announcementData).subscribe();
    });
  }

  isParticipantAlready(announcementId: number, announcement: AnnouncementModel): any {

    return this.participateService.isParticipantAlready(announcementId).pipe(map(
      data => {
        // tslint:disable-next-line:triple-equals
        announcement.isParticipant = data == true;
      }, (error: any) => {
        console.log(error);
      }
    ));
  }

}
