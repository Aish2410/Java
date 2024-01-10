import { Component } from "@angular/core";
import { Router } from "@angular/router";
import { ApplicantDetailsDTO } from "src/app/interfaces";
import { AlertService } from "src/app/services/alert.service";
import { ApplicantService } from "src/app/services/applicant.service";

@Component({
  selector: "app-application-details",
  templateUrl: "./application-details.component.html",
  styleUrls: ["./application-details.component.scss"],
})
export class ApplicationDetailsComponent {
  public application: ApplicantDetailsDTO | null = null;
  constructor(
    private router: Router,
    private applicant: ApplicantService,
    private alert: AlertService
  ) {}

  ngOnInit() {
    this.applicant.getByEmail().subscribe((data: any) => {
      this.application = data.response;
    });
  }
}
