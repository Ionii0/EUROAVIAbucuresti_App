import {Component, OnInit} from '@angular/core';
import {MenuItem, PrimeIcons} from "primeng/api";
import {AuthService} from "../auth/auth/auth.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  public itemsLoggedInAsUser: MenuItem[] = [];
  public itemsLoggedOut: MenuItem[] = [];
  public itemsLoggedInAsAdmin:MenuItem[]=[];
  public isAdmin: boolean=false;
  public alreadyCheckedRole:boolean=false;

  constructor(private authService: AuthService, private router: Router) {
    router.events.subscribe(() => {
      if(this.isLoggedIn() && !this.alreadyCheckedRole){
        this.initializeIsAdmin();
        this.alreadyCheckedRole=true;
      }
    });
  }

  ngOnInit(): void {
    this.itemsLoggedInAsAdmin = [
      {
        label: 'LogOut',
        icon: PrimeIcons.EJECT,
        command: () => {
          this.logout()
        }
      },
      {
        label: 'Announcements',
        icon: PrimeIcons.INBOX,
        command: () => {
          this.goToAnnouncements()
        }
      },
      {
        label: 'Admin',
        icon: PrimeIcons.USER,
        command: () => {
          this.goToAdminPage();
        }
      },
      {
        label:'Calendar',
        icon:PrimeIcons.CALENDAR,
        command:()=>{
          this.goToCalendarPage();
        }
      }
    ];
    this.itemsLoggedInAsUser = [
      {
        label: 'LogOut',
        icon: PrimeIcons.EJECT,
        command: () => {
          this.logout()
        }
      },
      {
        label: 'Announcements',
        icon: PrimeIcons.INBOX,
        command: () => {
          this.goToAnnouncements()
        }
      },
      {
        label:'Calendar',
        icon:PrimeIcons.CALENDAR,
        command:()=>{
          this.goToCalendarPage();
        }
      }
    ];
    this.itemsLoggedOut = [
      {
        label: 'LogIn',
        icon: PrimeIcons.KEY,
        command: () => {
          this.login()
        }
      },
      {
        label: 'SignUp',
        icon: PrimeIcons.USER_PLUS,
        command: () => {
          this.signup();
        }
      }
    ];

  }

  logout() {
    this.authService.logout();
    this.alreadyCheckedRole=false;
    this.router.navigateByUrl('/login').then(() => {
    });
  }

  isLoggedIn(): Boolean {
    return this.authService.isAuthenticated();
  }

  login() {
    this.router.navigateByUrl('/login').then(() => {
    });
  }

  signup() {
    this.router.navigateByUrl('/signup').then(() => {
    });
  }

  goToAnnouncements() {
    this.router.navigateByUrl('/announcement-page').then(() => {
    });
  }
  goToCalendarPage(){
    this.router.navigateByUrl('/calendar-page').then(() => {
    });
  }
  goToAdminPage() {
    this.router.navigateByUrl('/admin-page').then(() => {
    });
  }

  initializeIsAdmin() {
    this.authService.isAdmin().subscribe(data=>{
      this.isAdmin = data == true;
    })
    return this.isAdmin;
  }
}
