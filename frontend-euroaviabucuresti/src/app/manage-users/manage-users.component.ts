import {Component, OnInit} from '@angular/core';
import {ManageUsersService} from '../services/manage-users/manage-users.service';
import {ManageUsersModel} from '../services/manage-users/manage-users-model';
import {MenuItem} from 'primeng/api';

@Component({
  selector: 'app-manage-users',
  templateUrl: './manage-users.component.html',
  styleUrls: ['./manage-users.component.css']
})
export class ManageUsersComponent implements OnInit {

  public arrayUsers: Array<ManageUsersModel> = [];


  constructor(private manageUsersService: ManageUsersService) {
    this.manageUsersService.getAllUsers().subscribe((data: Array<ManageUsersModel>) => {
      data.forEach(user => {
        if (user.firstName == null)
          data = data.filter(x => x!= user);
      })
      this.arrayUsers = data;
      console.log(this.arrayUsers);

    });
  }

  ngOnInit(): void {
  }

}
