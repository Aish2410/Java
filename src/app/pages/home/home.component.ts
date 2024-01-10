import { Component } from '@angular/core';
import { CourseDTO } from 'src/app/interfaces';
import { AlertService } from 'src/app/services/alert.service';
import { CourseService } from 'src/app/services/course.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent {

  public courseList: CourseDTO[] = [];
  constructor(private alert: AlertService, private courses: CourseService) { }

  ngOnInit() {
    this.courses.getAll().subscribe((res: any) => {
      this.courseList = res.response || [];
    }, this.alert.apiFail);
  }
}
