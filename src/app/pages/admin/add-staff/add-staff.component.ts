import { Component } from "@angular/core";
import { NgForm } from "@angular/forms";
import { UniversityStraffMemberDTO, UserDTO } from "src/app/interfaces";
import { AlertService } from "src/app/services/alert.service";
import { AuthService } from "src/app/services/auth.service";
import { StaffService } from "src/app/services/staff.service";

@Component({
  selector: "app-add-staff",
  templateUrl: "./add-staff.component.html",
  styleUrls: ["./add-staff.component.scss"],
})
export class AddStaffComponent {
  public staffList: UniversityStraffMemberDTO[] = [];
  public selectedUser: UniversityStraffMemberDTO = {
    active: false,
    email: "",
    password: "",
    role: "",
    staffId: 0,
  };

  displayStyle = "none";

  constructor(
    private alert: AlertService,
    private auth: AuthService,
    private staffService: StaffService
  ) {}

  ngOnInit() {
    this.staffService.getAll().subscribe((res: any) => {
      this.staffList = res.response || [];
    });
  }

  onSubmit(ngForm: NgForm) {
    if (ngForm.form.invalid) {
      this.alert.error("Please fill required elements");
      return;
    }

    const credentials: UserDTO = { ...ngForm.form.value, role: ["STAFF"] };

    this.auth.register(credentials);
    ngForm.resetForm();
    this.ngOnInit();
  }

  deleteUser(userId: number) {
    this.staffService.delete(userId).subscribe((res: any) => {
      this.alert.apiSuccess(res);
      this.ngOnInit();
    }, this.alert.apiFail);
  }

  openPopup(selUser: UniversityStraffMemberDTO) {
    this.selectedUser = { ...selUser, password: "" };
    this.displayStyle = "block";
  }
  closePopup() {
    this.displayStyle = "none";
  }

  onUpdate() {
    this.staffService.update(this.selectedUser).subscribe((res: any) => {
      this.closePopup();
      this.alert.apiSuccess(res);
      this.ngOnInit();
    }, this.alert.apiFail);
  }
}
