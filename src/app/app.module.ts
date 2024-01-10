import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './pages/login/login.component';
import { RegisterComponent } from './pages/register/register.component';
import { HomeComponent } from './pages/home/home.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MaterialExampleModule } from 'src/material.module';
import { FormsModule } from '@angular/forms';
import { AlertService } from './services/alert.service';
import { HttpClientModule } from '@angular/common/http';
import { ApiService } from './services/api.service';
import { AuthService } from './services/auth.service';
import { CoursesComponent } from './pages/admin/courses/courses.component';
import { CourseService } from './services/course.service';
import { BasicComponent } from './pages/layouts/basic/basic.component';
import { AdminComponent } from './pages/layouts/admin/admin.component';
import { StaffComponent } from './pages/layouts/staff/staff.component';
import { DashboardComponent } from './pages/admin/dashboard/dashboard.component';
import { ForgotPasswordComponent } from './pages/forgot-password/forgot-password.component';
import { AddApplicantComponent } from './pages/student/add-applicant/add-applicant.component';
import { ApplicantService } from './services/applicant.service';
import { AddStaffComponent } from './pages/admin/add-staff/add-staff.component';
import { StaffService } from './services/staff.service';
import { AdmissionPageComponent } from './pages/admin/admission-page/admission-page.component';
import { AdmissionService } from './services/admission.service';
import { StaffDashboardComponent } from './pages/staff/staff-dashboard/staff-dashboard.component';
import { ApplicationDetailsComponent } from './pages/student/application-details/application-details.component';
import { StudentComponent } from './pages/layouts/student/student.component';
import { HomeDashboardComponent } from './pages/home-dashboard/home-dashboard.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    HomeComponent,
    CoursesComponent,
    BasicComponent,
    AdminComponent,
    StaffComponent,
    DashboardComponent,
    ForgotPasswordComponent,
    AddApplicantComponent,
    AddStaffComponent,
    AdmissionPageComponent,
    StaffDashboardComponent,
    ApplicationDetailsComponent,
    StudentComponent,
    HomeDashboardComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    HttpClientModule,
    MaterialExampleModule,
    BrowserAnimationsModule
  ],
  providers: [
    AlertService,
    ApiService,
    AuthService,
    CourseService,
    ApplicantService,
    StaffService,
    AdmissionService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
