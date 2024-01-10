import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ApplicantDTO, CourseDTO } from 'src/app/interfaces';
import { AlertService } from 'src/app/services/alert.service';
import { ApplicantService } from 'src/app/services/applicant.service';
import { AuthService } from 'src/app/services/auth.service';
import { CourseService } from 'src/app/services/course.service';

@Component({
  selector: 'app-add-applicant',
  templateUrl: './add-applicant.component.html',
  styleUrls: ['./add-applicant.component.scss']
})
export class AddApplicantComponent {

  public courses: CourseDTO[] = [];
  constructor(private alert: AlertService, private courseService: CourseService, private applicant: ApplicantService) { }


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
    credentials.email = sessionStorage.getItem('SESSION_EMAIL') || '';
    this.applicant.addApplicant(credentials);
    ngForm.resetForm();
  }
}
