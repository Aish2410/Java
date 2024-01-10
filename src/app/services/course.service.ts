import { Injectable } from '@angular/core';
import { CourseDTO } from '../interfaces';
import { AlertService } from './alert.service';
import { ApiService } from './api.service';

@Injectable({
  providedIn: 'root'
})
export class CourseService {

  constructor(private api: ApiService, private alert: AlertService) { }

  add(course: CourseDTO) {
    return this.api.postAuth('/course/add', course);
  }

  getAll() {
    return this.api.getAuth('/course/get/All');
  }

  delete(courseId: number) {
    return this.api.deleteAuth(`/course/remove/course/${courseId}`);
  }
  update(course: CourseDTO) {
    return this.api.postAuth('/course/update', course);
  }

}