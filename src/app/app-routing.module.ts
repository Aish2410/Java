import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { AppComponent } from "./app.component";
import { DashboardComponent } from "./pages/admin/dashboard/dashboard.component";
import { CoursesComponent } from "./pages/admin/courses/courses.component";
import { HomeComponent } from "./pages/home/home.component";
import { AdminComponent } from "./pages/layouts/admin/admin.component";
import { BasicComponent } from "./pages/layouts/basic/basic.component";
import { LoginComponent } from "./pages/login/login.component";
import { RegisterComponent } from "./pages/register/register.component";
import { ForgotPasswordComponent } from "./pages/forgot-password/forgot-password.component";
import { AddApplicantComponent } from "./pages/student/add-applicant/add-applicant.component";
import { AddStaffComponent } from "./pages/admin/add-staff/add-staff.component";
import { AdmissionPageComponent } from "./pages/admin/admission-page/admission-page.component";
import { StaffComponent } from "./pages/layouts/staff/staff.component";
import { StaffDashboardComponent } from "./pages/staff/staff-dashboard/staff-dashboard.component";
import { ApplicationDetailsComponent } from "./pages/student/application-details/application-details.component";
import { AuthGuard } from "./gurds/auth.guard";
import { StaffGuard } from "./gurds/staff.guard";
import { AdminGuard } from "./gurds/admin.guard";
import { StudentComponent } from "./pages/layouts/student/student.component";
import { HomeDashboardComponent } from "./pages/home-dashboard/home-dashboard.component";

const routes: Routes = [
  {
    path: "",
    component: BasicComponent,
    // pathMatch: 'full',
    children: [
      {
        path: "",
        component: HomeDashboardComponent,
      },
      {
        path: "courses",
        component: HomeComponent,
      },

      {
        path: "login",
        component: LoginComponent,
      },
      {
        path: "register",
        component: RegisterComponent,
      },
      {
        path: "forgot-password",
        component: ForgotPasswordComponent,
      },
    ],
  },
  {
    path: "admin",
    component: AdminComponent,
    canActivate: [AuthGuard, AdminGuard],
    canActivateChild: [AuthGuard, AdminGuard],
    children: [
      {
        path: "",
        component: DashboardComponent,
      },
      {
        path: "courses",
        component: CoursesComponent,
      },
      {
        path: "add-staff",
        component: AddStaffComponent,
      },
      {
        path: "add-admission",
        component: AdmissionPageComponent,
      },
    ],
  },
  {
    path: "staff",
    component: StaffComponent,
    canActivate: [AuthGuard, StaffGuard],
    canActivateChild: [AuthGuard, StaffGuard],
    children: [
      {
        path: "",
        component: StaffDashboardComponent,
      },
      {
        path: "courses",
        component: CoursesComponent,
      },
      {
        path: "add-admission",
        component: AdmissionPageComponent,
      },
    ],
  },
  {
    path: "student",
    canActivate: [AuthGuard],
    canActivateChild: [AuthGuard],
    component: StudentComponent,
    children: [
      {
        path: "",
        component: ApplicationDetailsComponent,
      },
      {
        path: "add-applicant",
        component: AddApplicantComponent,
      },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
