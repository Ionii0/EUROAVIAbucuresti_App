import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { faComments, faEye, faPlane } from '@fortawesome/free-solid-svg-icons';
import { throwError } from 'rxjs';
import { AuthService } from 'src/app/auth/shared/auth.service';
import { AnnouncementModel } from 'src/app/services/announcement-model';
import { AnnouncementService } from 'src/app/services/announcement.service';
import { CommentPayload } from 'src/app/services/comments/comment-payload.payload';
import { CommentsService } from 'src/app/services/comments/comments.service';
import { ParticipatePayload } from 'src/app/services/participate/participate-payload.payload';
import { ParticipateService } from 'src/app/services/participate/participate.service';

@Component({
  selector: 'app-view-announcement',
  templateUrl: './view-announcement.component.html',
  styleUrls: ['./view-announcement.component.css']
})
export class ViewAnnouncementComponent implements OnInit {

  announcementId:number;
  announcement:AnnouncementModel;
  commentForm: FormGroup;
  commentPayload: CommentPayload;
  comments:CommentPayload[];
  faComments=faComments;
  faPlane=faPlane;
  faEye=faEye;
  viewAll:boolean;
  participatePayload:ParticipatePayload;
 

  constructor(private announcementService:AnnouncementService, private activatedRoute:ActivatedRoute, private commentService:CommentsService, private authService:AuthService,private participateService:ParticipateService) {

    this.announcementId=this.activatedRoute.snapshot.params.id;
    this.commentForm=new FormGroup({
      body:new FormControl('',Validators.required)
      });
    
    this.commentPayload={
      mailEuroavia:this.authService.getMailEuroavia(),
      body:'',
      announcementId:this.announcementId
    } ; 
    
   }

  ngOnInit(): void {
    this.getCommentsForAnnouncement();
    this.getAnnouncementById();
  }

  postComment() {
    this.commentPayload.body = this.commentForm.get('body').value;
    this.commentService.postComment(this.commentPayload).subscribe(data => {
      this.commentForm.get('body').setValue('');
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
      },error=>{
        throwError(error);
      });
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

}
