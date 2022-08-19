import { Component, OnInit } from '@angular/core';
import { faComments, faEye, faPlane,faPlaneArrival } from '@fortawesome/free-solid-svg-icons';
import {AnnouncementService} from "../../services/announcement/announcement.service";
import {AnnouncementModel} from "../../services/announcement/announcement-model";
import {ActivatedRoute} from "@angular/router";
import {CommentsService} from "../../services/comments/comments.service";
import {ParticipatePayload} from "../../services/participate/participate-payload.payload";
import {AuthService} from "../../auth/auth/auth.service";
import {ParticipateService} from "../../services/participate/participate.service";
import {CommentPayload} from "../../services/comments/comment-payload.payload";
import {map} from "rxjs/operators";
import {throwError} from "rxjs";

@Component({
  selector: 'app-view-announcement',
  templateUrl: './view-announcement.component.html',
  styleUrls: ['./view-announcement.component.css']
})
export class ViewAnnouncementComponent implements OnInit {

  public announcementId:number;
  public announcement!:AnnouncementModel;
  public commentPayload: CommentPayload;
  public comments!:CommentPayload[];
  public faComments=faComments;
  public faPlane=faPlane;
  public faPlaneArrival=faPlaneArrival;
  public faEye=faEye;
  public participatePayload!:ParticipatePayload;
  public body: string="";
  isParticipant: boolean = false;


  constructor(private announcementService:AnnouncementService, private activatedRoute:ActivatedRoute, private commentService:CommentsService, private authService:AuthService,private participateService:ParticipateService) {

    this.announcementId=this.activatedRoute.snapshot.params.id;

    this.commentPayload={
      mailEuroavia:this.authService.getMailEuroavia(),
      body:'',
      announcementId:this.announcementId
    } ;
    this.getAnnouncementById();
    this.getCommentsForAnnouncement();
  }

  ngOnInit(): void {
    this.isParticipantAlready(this.announcementId,this.announcement).subscribe();
  }

  postComment() {
    this.commentPayload.body = this.body;
    this.commentService.postComment(this.commentPayload).subscribe(data => {
      this.body="";
      this.getCommentsForAnnouncement();
    }, error => {
      throwError(error);
    })
  }
  getCommentsForAnnouncement() {
    this.commentService.getAllCommentsForPost(this.announcementId).subscribe(data => {
      this.comments = data;
    }, error => {
      throwError(error);
    });
  }

  getAnnouncementById(){
    this.announcementService.getAnnouncement(this.announcementId).subscribe(
      data=>{
        this.announcement=data;
        console.log(data);
      },error=>{
        throwError(error);
      });
  }
  participateOrWithdraw(id: number): void {
    this.participatePayload = {
      announcementId: id,
      mailEuroavia: this.authService.getMailEuroavia()
    };
    if(this.isParticipant)
      this.announcement.participantCount--;
    else this.announcement.participantCount++;
    this.participateService.participateOrWithdraw(this.participatePayload).subscribe(() => {
      this.isParticipantAlready(this.announcement?.id, this.announcement).subscribe();
    });
  }

  isParticipantAlready(announcementId: number, announcement: AnnouncementModel): any {

    return this.participateService.isParticipantAlready(announcementId).pipe(map(
      data => {
        // tslint:disable-next-line:triple-equals
        this.isParticipant = data == true;
      }, (error: any) => {
        console.log(error);
      }
    ));
  }

}
