import { Component, OnInit } from '@angular/core';
import { AnnouncementModel } from '../shared/announcement-model';
import { AnnouncementService } from '../shared/announcement.service';
import {faComments, faEye, faPlane} from '@fortawesome/free-solid-svg-icons';
@Component({
  selector: 'app-announcements',
  templateUrl: './announcements.component.html',
  styleUrls: ['./announcements.component.css']
})
export class AnnouncementsComponent implements OnInit {
  announcements: Array<AnnouncementModel>=[];
  faComments=faComments;
  faPlane=faPlane;
  faEye=faEye;
  viewAll:boolean;

  constructor(private announcementService:AnnouncementService) { 
    this.announcementService.getAllAnnouncements().subscribe(announcement=>{
      this.announcements=announcement;
    });
  }

  ngOnInit(): void {
  }

}
