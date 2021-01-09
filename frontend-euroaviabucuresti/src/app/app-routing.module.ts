import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AnnouncementsComponent } from './announcements/announcements.component';
import { CreateAnnouncementComponent } from './announcements/create-announcement/create-announcement.component';
import { UserGuard } from './auth/guard/user.guard';
import { LoginComponent } from './auth/login/login.component';
import { SignupComponent } from './auth/signup/signup.component';

const routes: Routes = [
  {path:'signup', component:SignupComponent},
  {path:'login', component:LoginComponent},
  {path:'announcement-page', component:AnnouncementsComponent, canActivate:[UserGuard]},
  {path:'create-announcement', component:CreateAnnouncementComponent, canActivate:[UserGuard]}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
