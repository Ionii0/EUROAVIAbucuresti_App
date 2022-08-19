import {Component, EventEmitter, Input, OnInit, Output, ViewChild} from '@angular/core';
import {ManageUsersModel} from "../../services/manage-users/manage-users-model";
import {UpdatePointsService} from "../../services/update-points/update-points.service";
import {Dialog} from "primeng/dialog";

@Component({
  selector: 'app-update-points',
  templateUrl: './update-points.component.html',
  styleUrls: ['./update-points.component.css']
})
export class UpdatePointsComponent implements OnInit {
  @Input()
  showDialog!: boolean;
  @Input()
  user!: ManageUsersModel;

  @Output()
  public deleteSelectedUSer:EventEmitter<boolean>=new EventEmitter<boolean>();

  @ViewChild('dialog')
  dialog!:Dialog;

  public newPointsValue: number = 0;


  constructor(private updatePointsService:UpdatePointsService) {
  }

  ngOnInit(): void {
    this.newPointsValue = this.user.activityPoints;
  }

  updatePoints(event:Event) {
    this.updatePointsService.updatePoints(this.user.id,this.newPointsValue).subscribe();
    this.user.activityPoints=this.newPointsValue;
    this.deleteSelectedUSer.emit(true);
    this.dialog.close(event);

  }
}
