import { Injectable } from '@angular/core';
import { ApplicantDTO } from '../interfaces';
import { ApiService } from './api.service';

@Injectable({
  providedIn: 'root'
})
export class AdmissionService {

  constructor(private api: ApiService) { }

  getByCourseId(courseId: number) {
    return this.api.getAuth(`/admission/view/by/courseId/${courseId}`);
  }

  update(data: ApplicantDTO) {
    return this.api.postAuth('/admission/update', data);
  }
  delete(appId: number) {
    return this.api.deleteAuth(``)
  }
}
