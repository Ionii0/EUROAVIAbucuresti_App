import {Component, EventEmitter, Input, OnInit, Output, ViewChild} from '@angular/core';
import {ManageUsersModel} from "../../services/manage-users/manage-users-model";
import {UpdateTokensService} from "../../services/update-tokens/update-tokens.service";
import {Dialog} from "primeng/dialog";


@Component({
  selector: 'app-update-tokens',
  templateUrl: './update-tokens.component.html',
  styleUrls: ['./update-tokens.component.css']
})
export class UpdateTokensComponent implements OnInit {
  @Input()
  public showDialog!: boolean;
  @Input()
  public user!: ManageUsersModel;

  @Output()
  public deleteSelectedUSer:EventEmitter<boolean>=new EventEmitter<boolean>();

  @ViewChild('dialog')
  dialog!:Dialog;

  public newTokensValue: number = 0;

  constructor(private updateTokensService:UpdateTokensService) {
  }

  ngOnInit(): void {
    this.newTokensValue = this.user.virtualToken;
  }

  updateTokens(event:Event) {
    this.updateTokensService.updateTokens(this.user.id, this.newTokensValue).subscribe();
    this.user.virtualToken=this.newTokensValue;
    this.deleteSelectedUSer.emit(true);
    this.dialog.close(event);


  }
}
