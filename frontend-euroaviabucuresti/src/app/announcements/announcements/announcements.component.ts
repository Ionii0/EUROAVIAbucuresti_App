import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {AnnouncementModel} from "../../services/announcement/announcement-model";
import {AnnouncementService} from "../../services/announcement/announcement.service";
import {AuthService} from "../../auth/auth/auth.service";
import {ParticipateService} from "../../services/participate/participate.service";

@Component({
  selector: 'app-announcements',
  templateUrl: './announcements.component.html',
  styleUrls: ['./announcements.component.css'],

})
export class AnnouncementsComponent implements OnInit {
  announcements: AnnouncementModel[] =[];

  constructor(private announcementService: AnnouncementService, private router: Router,
              private participateService: ParticipateService, private authService: AuthService) {

    this.announcementService.getAllAnnouncements().subscribe(announcement => {
      this.announcements = announcement;
    });
  }

  ngOnInit(): void {
  }

}
