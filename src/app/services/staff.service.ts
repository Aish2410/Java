import { Injectable } from '@angular/core';
import { UniversityStraffMemberDTO } from '../interfaces';
import { AlertService } from './alert.service';
import { ApiService } from './api.service';

@Injectable({
  providedIn: 'root'
})
export class StaffService {

  constructor(private api: ApiService, private alert: AlertService) { }

  getAll() {
    return this.api.getAuth('/staff/member/get/All');
  }

  update(staff: UniversityStraffMemberDTO) {
    return this.api.postAuth('/staff/member/update', staff);
  }
  delete(id: number) {
    return this.api.deleteAuth(`/staff/member/remove/${id}`);
  }
}
