import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-staff',
  templateUrl: './staff.component.html',
  styleUrls: ['./staff.component.scss']
})
export class StaffComponent {
  constructor(private router: Router) { }
  onLogout() {
    sessionStorage.clear();
    this.router.navigateByUrl("/login");
  }
}
