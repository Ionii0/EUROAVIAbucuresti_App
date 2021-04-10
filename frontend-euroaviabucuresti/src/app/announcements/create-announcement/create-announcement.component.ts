import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { error } from 'protractor';
import { throwError } from 'rxjs';
import { AnnouncementCreatePayload } from 'src/app/services/announcement-create-payload.payload';
import { AnnouncementService } from 'src/app/services/announcement.service';

@Component({
  selector: 'app-create-announcement',
  templateUrl: './create-announcement.component.html',
  styleUrls: ['./create-announcement.component.css']
})
export class CreateAnnouncementComponent implements OnInit {
  createAnnouncementForm:FormGroup;
  announcementCreatePayload:AnnouncementCreatePayload;
  title=new FormControl('');
  body=new FormControl('');
  constructor(private announcementService:AnnouncementService, private router:Router) {
    this.announcementCreatePayload={
      title:'',
      body:''
    }
   }

  ngOnInit(): void {
    this.createAnnouncementForm=new FormGroup({
      title: new FormControl('', Validators.required),
      body: new FormControl('', Validators.required)

    });
  }
  createAnnouncement(){
    this.announcementCreatePayload.title=this.createAnnouncementForm.get('title').value;
    this.announcementCreatePayload.body=this.createAnnouncementForm.get('body').value;

    this.announcementService.createAnnouncement(this.announcementCreatePayload).subscribe((data)=>
    {
      this.router.navigateByUrl('/');
    },error=>{
      throwError(error);
    })
  }
}
