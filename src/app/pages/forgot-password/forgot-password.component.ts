import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ForgotPasswordDTO } from 'src/app/interfaces';
import { AlertService } from 'src/app/services/alert.service';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-forgot-password',
  templateUrl: './forgot-password.component.html',
  styleUrls: ['./forgot-password.component.scss']
})
export class ForgotPasswordComponent {

  public roles: string[] = [
    'ACM', 'APPLICANT', 'STAFF'
  ]
  constructor(private alert: AlertService, private auth: AuthService) { }


  onSubmit(ngForm: NgForm) {
    if (ngForm.form.invalid) {
      this.alert.error("Please fill required elements");
      return;
    }
    const credentials: ForgotPasswordDTO = ngForm.form.value;

    this.auth.forgotPassword(credentials);
    ngForm.resetForm();
  }
}
