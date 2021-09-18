import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {HeaderComponent} from './header/header.component';
import {SignupComponent} from './auth/signup/signup.component';
import {ReactiveFormsModule} from '@angular/forms';
import {HttpClientModule, HTTP_INTERCEPTORS} from '@angular/common/http';
import {LoginComponent} from './auth/login/login.component';
import {NgxWebstorageModule} from 'ngx-webstorage';
import {ToastrModule} from 'ngx-toastr';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {AnnouncementsComponent} from './announcements/announcements.component';
import {FontAwesomeModule} from '@fortawesome/angular-fontawesome';
import {CreateAnnouncementComponent} from './announcements/create-announcement/create-announcement.component';
import {AdminComponent} from './admin/admin.component';
import {TokenInterceptor} from './token-interceptor';
import {EditorModule} from '@tinymce/tinymce-angular';
import {CommentsComponent} from './comments/comments.component';
import {ViewAnnouncementComponent} from './announcements/view-announcement/view-announcement.component';
import {ManageUsersComponent} from './manage-users/manage-users.component';
import {TableModule} from "primeng/table";
import {CardModule} from "primeng/card";


@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    SignupComponent,
    LoginComponent,
    AnnouncementsComponent,
    CreateAnnouncementComponent,
    AdminComponent,
    CommentsComponent,
    ViewAnnouncementComponent,
    ManageUsersComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
    NgxWebstorageModule.forRoot(),
    BrowserAnimationsModule,
    ToastrModule.forRoot(),
    FontAwesomeModule,
    EditorModule,
    TableModule,
    CardModule
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptor,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
