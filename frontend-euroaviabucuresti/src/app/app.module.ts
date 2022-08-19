import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {AppComponent} from './app.component';
import {RouterModule} from "@angular/router";
import {AnnouncementsComponent} from "./announcements/announcements/announcements.component";
import {AdminComponent} from "./admin/admin.component";
import {LoginComponent} from "./auth/login/login.component";
import {HeaderComponent} from "./header/header.component";
import {CommentsComponent} from "./comments/comments.component";
import {SignupComponent} from "./auth/signup/signup.component";
import {ManageUsersComponent} from "./manage-users/manage-users/manage-users.component";
import {MenubarModule} from "primeng/menubar";
import {CardModule} from "primeng/card";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {NgxWebstorageModule} from "ngx-webstorage";
import {AppRoutingModule} from "./app-routing.module";
import {PasswordModule} from 'primeng/password';
import {InputTextModule} from 'primeng/inputtext';
import {ButtonModule} from "primeng/button";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {ToastrModule} from "ngx-toastr";
import {CommonModule} from "@angular/common";
import {AnnouncementCardComponent} from './announcements/announcement-card/announcement-card.component';
import {TokenInterceptor} from "./token-interceptor";
import {DividerModule} from "primeng/divider";
import {FontAwesomeModule} from '@fortawesome/angular-fontawesome';
import {CreateAnnouncementComponent} from "./announcements/create-announcement/create-announcement.component";
import {EditorModule} from "primeng/editor";
import {ViewAnnouncementComponent} from './announcements/view-announcement/view-announcement.component';
import {InputTextareaModule} from "primeng/inputtextarea";
import {TableModule} from "primeng/table";
import {ContextMenuModule} from "primeng/contextmenu";
import {UpdateTokensComponent} from './manage-users/update-tokens/update-tokens.component';
import {UpdatePointsComponent} from './manage-users/update-points/update-points.component';
import {InputNumberModule} from "primeng/inputnumber";
import {DialogModule} from "primeng/dialog";
import {FullCalendarModule} from "@fullcalendar/angular";
import dayGridPlugin from '@fullcalendar/daygrid'; // a plugin!
import interactionPlugin from '@fullcalendar/interaction';
import timeGridPlugin from '@fullcalendar/timegrid';
import listPlugin from '@fullcalendar/list';
import {CalendarComponent} from './calendar/calendar.component';
import {CalendarModule} from "primeng/calendar";
import { ManageProjectsComponent } from './manage-projects/manage-projects/manage-projects.component';
import { KanbanComponent } from './manage-projects/kanban/kanban.component';
import {DragDropModule} from 'primeng/dragdrop';
FullCalendarModule.registerPlugins([ // register FullCalendar plugins
  dayGridPlugin,
  interactionPlugin,
  timeGridPlugin,
  listPlugin
]);

@NgModule({
  declarations: [
    AppComponent,
    AnnouncementsComponent,
    AdminComponent,
    LoginComponent,
    SignupComponent,
    CommentsComponent,
    HeaderComponent,
    ManageUsersComponent,
    AnnouncementCardComponent,
    CreateAnnouncementComponent,
    ViewAnnouncementComponent,
    UpdateTokensComponent,
    UpdatePointsComponent,
    CalendarComponent,
    ManageProjectsComponent,
    KanbanComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    CommonModule,
    AppRoutingModule,
    FormsModule,
    RouterModule,
    ReactiveFormsModule,
    HttpClientModule,
    NgxWebstorageModule.forRoot(),
    CardModule,
    MenubarModule,
    PasswordModule,
    InputTextModule,
    ButtonModule,
    ToastrModule.forRoot(),
    DividerModule,
    FontAwesomeModule,
    EditorModule,
    InputTextareaModule,
    TableModule,
    ContextMenuModule,
    InputNumberModule,
    DialogModule,
    FullCalendarModule,
    CalendarModule,
    DragDropModule
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptor,
      multi: true
    }
  ],
  bootstrap: [AppComponent],
})
export class AppModule {
}
