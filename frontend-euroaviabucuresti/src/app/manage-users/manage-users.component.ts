import {Component, OnInit} from '@angular/core';
import {ManageUsersService} from '../services/manage-users/manage-users.service';
import {ManageUsersModel} from '../services/manage-users/manage-users-model';

@Component({
  selector: 'app-manage-users',
  templateUrl: './manage-users.component.html',
  styleUrls: ['./manage-users.component.css']
})
export class ManageUsersComponent implements OnInit {

  public arrayUsers: Array<ManageUsersModel> = [];

  constructor(private manageUsersService: ManageUsersService) {
    this.manageUsersService.getAllUsers().subscribe((data: Array<ManageUsersModel>) => {
      this.arrayUsers = data;
      console.log(this.arrayUsers);
    });
  }

  ngOnInit(): void {
  }

}
