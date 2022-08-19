import {Component, OnInit, ViewChild} from '@angular/core';
import {ManageUsersModel} from "../../services/manage-users/manage-users-model";
import {ManageUsersService} from "../../services/manage-users/manage-users.service";
import {MenuItem, PrimeIcons} from "primeng/api";
import {Table} from "primeng/table";
import {ContextMenu} from "primeng/contextmenu";

@Component({
  selector: 'app-manage-users',
  templateUrl: './manage-users.component.html',
  styleUrls: ['./manage-users.component.css']
})
export class ManageUsersComponent implements OnInit {

  @ViewChild("dt1")
  public dt!: Table;
  @ViewChild("cm")
  public cm!: ContextMenu;


  public arrayUsers: Array<ManageUsersModel> = [];
  public clickMenu:MenuItem[];
  public selectedUser!:ManageUsersModel;
  public showPointsDialog:boolean=false;
  public showTokensDialog:boolean=false;

  constructor(private manageUsersService: ManageUsersService) {
    this.manageUsersService.getAllUsers().subscribe((data: Array<ManageUsersModel>) => {
      data.forEach(user => {
        if (user.firstName == null || user.lastName =="" || user.id <0)
          data = data.filter(x => x!= user);
      })
      this.arrayUsers = data;
      console.log(this.arrayUsers);
    });

    this.clickMenu=[
      {
        label:'Update Tokens',
        icon:PrimeIcons.DOLLAR,
        command: ()=>{this.updateTokens()}
      },
      {
        label:'Update Points',
        icon:PrimeIcons.CHEVRON_CIRCLE_UP,
        command: ()=>{this.updatePoints()}
      }
    ];

  }

  public updateTokens() {
    this.showTokensDialog=true;
  }
  public updatePoints() {
    this.showPointsDialog=true;
  }
  ngOnInit(): void {}
  clear(table: Table) {
    table.clear();
  }

  applyFilterGlobal($event: Event, contains: string) {
    this.dt.filterGlobal(($event.target as HTMLInputElement).value, 'contains');
  }

  deleteSelectedUser() {
    this.dt.contextMenuSelection=undefined;
    this.showTokensDialog=false;
    this.showPointsDialog=false;
  }
}
