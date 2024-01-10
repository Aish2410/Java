import { Injectable } from '@angular/core';
import { ApplicantDTO } from '../interfaces';
import { AlertService } from './alert.service';
import { ApiService } from './api.service';

@Injectable({
  providedIn: 'root'
})
export class ApplicantService {

  constructor(private api: ApiService, private alert: AlertService) { }

  addApplicant(data: ApplicantDTO) {
    data.active = true;
    data.admissionStatus = '';
    data.applicantId = 0;

    this.api.post('/user/add/applicant', data).subscribe(this.alert.apiSuccess, this.alert.apiFail);
  }

  getByEmail() {
    const email = sessionStorage.getItem('SESSION_EMAIL') || '';
    return this.api.getAuth(`/applicant/view/by/${email}`);
  }
}
