import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { UserDTO } from 'src/app/interfaces';
import { AlertService } from 'src/app/services/alert.service';
import { AuthService } from 'src/app/services/auth.service';


@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent {

  public roles: string[] = [
    'ACM', 'APPLICANT', 'STAFF'
  ]
  constructor(private alert: AlertService, private auth: AuthService) { }

  onSubmit(ngForm: NgForm) {
    if (ngForm.form.invalid) {
      this.alert.error("Please fill required elements");
      return;
    }

    const credentials: UserDTO = { ...ngForm.form.value, role: [ngForm.form.value.role] };
    this.auth.register(credentials);
    ngForm.resetForm();

  }

}
