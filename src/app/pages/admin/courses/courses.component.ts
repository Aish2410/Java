import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import * as dayjs from 'dayjs';
import { CourseDTO } from 'src/app/interfaces';
import { AlertService } from 'src/app/services/alert.service';
import { CourseService } from 'src/app/services/course.service';

@Component({
  selector: 'app-courses',
  templateUrl: './courses.component.html',
  styleUrls: ['./courses.component.scss']
})
export class CoursesComponent {
  public courseList: CourseDTO[] = [];

  displayStyle = "none";

  public selectedCourse: CourseDTO = {
    active: false,
    couresDuration: '',
    couresEndDate: '',
    couresFees: '',
    couresName: '',
    couresStartDate: '',
    courseId: 0
  }


  constructor(private alert: AlertService, private courses: CourseService) { }


  ngOnInit() {
    this.courses.getAll().subscribe((res: any) => {
      this.courseList = res.response || [];
    }, this.alert.apiFail);
  }

  onSubmit(ngForm: NgForm) {
    if (ngForm.form.invalid) {
      this.alert.error("Please fill required elements");
      return;
    }

    const credentials: CourseDTO = ngForm.form.value;
    // this.auth.register(credentials);
    console.log('credentials', credentials);
    credentials.couresDuration = this.getDifferenceDate(credentials.couresStartDate, credentials.couresEndDate);
    credentials.active = true;
    credentials.courseId = 0;
    this.courses.add(credentials).subscribe((res: any) => {
      this.alert.apiSuccess(res);
      this.ngOnInit();
    }, this.alert.apiFail);
    ngForm.resetForm();

  }

  getDifferenceDate(cureDate: any, newDate: any) {
    const day1 = dayjs(cureDate);
    const day2 = dayjs(newDate);
    const x = day2.diff(day1, 'M', true);
    return parseFloat(x.toString()).toFixed(2);
  }

  deleteCourse(courseId: number) {
    this.courses.delete(courseId).subscribe((res: any) => {
      this.alert.apiSuccess(res);
      this.ngOnInit();
    }, this.alert.apiFail);
  }

  openPopup(selCourse: CourseDTO) {
    this.selectedCourse = { ...selCourse };
    this.displayStyle = "block";
  }
  closePopup() {
    this.displayStyle = "none";
  }

  onUpdate() {

    this.selectedCourse.couresDuration = this.getDifferenceDate(this.selectedCourse.couresStartDate, this.selectedCourse.couresEndDate);
    this.courses.update(this.selectedCourse).subscribe((res: any) => {
      this.closePopup();
      this.alert.apiSuccess(res);
      this.ngOnInit();
    }, this.alert.apiFail);
  }

}
