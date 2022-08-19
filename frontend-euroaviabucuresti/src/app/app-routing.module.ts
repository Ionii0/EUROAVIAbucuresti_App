import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {SignupComponent} from "./auth/signup/signup.component";
import {LoginComponent} from "./auth/login/login.component";
import {AnnouncementsComponent} from "./announcements/announcements/announcements.component";
import {UserGuard} from "./auth/guard/user.guard";
import {AdminComponent} from "./admin/admin.component";
import {AdminGuard} from "./auth/guard/admin.guard";
import {CreateAnnouncementComponent} from "./announcements/create-announcement/create-announcement.component";
import {ViewAnnouncementComponent} from "./announcements/view-announcement/view-announcement.component";
import {ManageUsersComponent} from "./manage-users/manage-users/manage-users.component";
import {CalendarComponent} from "./calendar/calendar.component";
import {KanbanComponent} from "./manage-projects/kanban/kanban.component";

const routes: Routes = [
  {path: 'signup', component: SignupComponent},
  {path: 'login', component: LoginComponent},
  {path: 'admin-page', component: AdminComponent, canActivate: [AdminGuard]},
  {path: 'manage-users', component: ManageUsersComponent, canActivate: [AdminGuard]},
  {path: 'announcement-page', component: AnnouncementsComponent, canActivate: [UserGuard]},
  {path: 'calendar-page', component: CalendarComponent, canActivate: [UserGuard]},
  {path: 'create-announcement', component: CreateAnnouncementComponent, canActivate: [UserGuard]},
  {path: 'kanban', component: KanbanComponent, canActivate: [UserGuard]},
  {path: 'view-announcement/:id', component: ViewAnnouncementComponent, canActivate: [UserGuard]}];
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
