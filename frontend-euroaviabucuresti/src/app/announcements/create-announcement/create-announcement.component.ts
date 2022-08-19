import {Component, OnInit} from '@angular/core';
import { faPlus} from '@fortawesome/free-solid-svg-icons';
import {AnnouncementCreatePayload} from "../../services/announcement/announcement-create-payload.payload";
import {AnnouncementService} from "../../services/announcement/announcement.service";
import {Router} from "@angular/router";
import {throwError} from "rxjs";

@Component({
  selector: 'app-create-announcement',
  templateUrl: './create-announcement.component.html',
  styleUrls: ['./create-announcement.component.css']
})
export class CreateAnnouncementComponent implements OnInit {
  announcementCreatePayload: AnnouncementCreatePayload;
  faPlus=faPlus;
  public title: string = "";
  public body: string = "";

  constructor(private announcementService: AnnouncementService, private router: Router) {
    this.announcementCreatePayload = {
      title: '',
      body: ''
    }
  }

  ngOnInit(): void {
  }

  createAnnouncement() {
    this.announcementCreatePayload.title = this.title;
    this.announcementCreatePayload.body = this.body;

    this.announcementService.createAnnouncement(this.announcementCreatePayload).subscribe((data) => {
      this.router.navigateByUrl('/announcement-page');
    }, error => {
      throwError(error);
    })
  }

}
