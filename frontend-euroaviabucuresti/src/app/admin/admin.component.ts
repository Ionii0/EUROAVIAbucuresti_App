import { Component, OnInit } from '@angular/core';
import {faBusinessTime, faPlus} from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {
  faPlus=faPlus;
  faBusinessTime=faBusinessTime;
  constructor() { }

  ngOnInit(): void {
  }

}
