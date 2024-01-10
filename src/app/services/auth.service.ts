import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { ForgotPasswordDTO, LoginDTO, UserDTO } from '../interfaces';
import { AlertService } from './alert.service';
import { ApiService } from './api.service';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private api: ApiService, private alert: AlertService, private router: Router) { }

  register(data: UserDTO) {
    data.active = true;
    data.id = 0;
    this.api.post('/user/register', data).subscribe((res: any) => {
      this.alert.success('Registration successful.')
    }, this.alert.apiFail);
  }

  login(data: LoginDTO) {
    this.api.post('/user/login', data).subscribe((res: any) => {
      console.log('res', res);
      sessionStorage.setItem('SESSION_TOKEN', res.response);
      sessionStorage.setItem('SESSION_ROLE', data.role);
      sessionStorage.setItem('SESSION_EMAIL', data.username);
      if (data.role === 'ACM')
        this.router.navigateByUrl("/admin")
      else if (data.role === 'APPLICANT')
        this.router.navigateByUrl("/student")
      else if (data.role === 'STAFF')
        this.router.navigateByUrl("/staff")

    }, this.alert.apiFail);
  }

  forgotPassword(data: ForgotPasswordDTO) {
    this.api.post('/user/forgot/password', data).subscribe(this.alert.apiSuccess, this.alert.apiFail);
  }

  isLoggedIn() {
    if (sessionStorage.getItem('SESSION_TOKEN') && sessionStorage.getItem('SESSION_ROLE') && sessionStorage.getItem('SESSION_EMAIL'))
      return true;
    return false;
  }

  isAdmin() {
    if (sessionStorage.getItem('SESSION_ROLE') && sessionStorage.getItem('SESSION_ROLE') === 'ACM')
      return true;
    return false;
  }

  isStaff() {
    if (sessionStorage.getItem('SESSION_ROLE') && sessionStorage.getItem('SESSION_ROLE') === 'STAFF')
      return true;
    return false;
  }

}
