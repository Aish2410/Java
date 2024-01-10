import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ApplicantDTO, CourseDTO, ApplicantDetailsDTO } from 'src/app/interfaces';
import { AdmissionService } from 'src/app/services/admission.service';
import { AlertService } from 'src/app/services/alert.service';
import { ApplicantService } from 'src/app/services/applicant.service';
import { CourseService } from 'src/app/services/course.service';

@Component({
  selector: 'app-admission-page',
  templateUrl: './admission-page.component.html',
  styleUrls: ['./admission-page.component.scss']
})
export class AdmissionPageComponent {
  public admissionList: ApplicantDetailsDTO[] = [];

  public statusList = ['APPLIED', 'PENDING', 'CONFIRMED', 'REJECTED'];
  selectedCourseId = 0;

  public selectedCourse: ApplicantDTO = {
    active: false,
    admissionStatus: '',
    applicantDegree: '',
    applicantGraduationPercent: 0,
    applicantId: 0,
    applicantMobileNumber: '',
    applicantName: '',
    courseId: 0,
    email: '',
  }

  displayStyle = "none";


  public courses: CourseDTO[] = [];
  constructor(private alert: AlertService, private courseService: CourseService, private applicant: ApplicantService, private admissionService: AdmissionService) { }

  ngOnInit() {
    this.courseService.getAll().subscribe((res: any) => {
      this.courses = res.response || [];
    }, this.alert.apiFail);
  }

  onSubmit(ngForm: NgForm) {
    if (ngForm.form.invalid) {
      this.alert.error("Please fill required elements");
      return;
    }
    const credentials: ApplicantDTO = ngForm.form.value;

    this.applicant.addApplicant(credentials);
    ngForm.resetForm();
  }

  onCourseChange(e: any) {
    this.selectedCourseId = e.target.value;
    if (e.target.value !== '#')
      this.getTableData();
  }

  getTableData() {
    this.admissionService.getByCourseId(this.selectedCourseId).subscribe((res: any) => {
      this.admissionList = res.response;
    }, this.alert.apiFail);
  }



  openPopup(selUser: ApplicantDetailsDTO) {
    this.selectedCourse = {
      active: false,
      admissionStatus: selUser.admission.admissionStatus,
      applicantDegree: selUser.applicantDegree,
      applicantGraduationPercent: selUser.applicantGraduationPercent,
      applicantId: selUser.id,
      applicantMobileNumber: selUser.applicantMobileNumber,
      applicantName: selUser.applicantName,
      courseId: selUser.admission.course.courseId,
      email: selUser.email,
    };
    this.displayStyle = "block";
  }
  closePopup() {
    this.displayStyle = "none";
  }

  onUpdate() {
    this.admissionService.update(this.selectedCourse).subscribe((res: any) => {
      this.closePopup();
      this.alert.apiSuccess(res);
      this.getTableData();
    }, this.alert.apiFail);
  }
}
