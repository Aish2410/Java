import { ComponentFixture, TestBed } from "@angular/core/testing";
import { HttpClientModule } from "@angular/common/http";
import { AddStaffComponent } from "./add-staff.component";
import { ApiService } from "src/app/services/api.service";
import { AuthService } from "src/app/services/auth.service";
import { FormsModule } from "@angular/forms";
describe("AddStaffComponent", () => {
  let component: AddStaffComponent;
  let fixture: ComponentFixture<AddStaffComponent>;
  let compiled: HTMLElement;
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [AddStaffComponent],
      providers: [ApiService, AuthService],
      imports: [HttpClientModule, FormsModule],
    }).compileComponents();

    fixture = TestBed.createComponent(AddStaffComponent);
    component = fixture.componentInstance;
    compiled = fixture.nativeElement as HTMLElement;
    fixture.detectChanges();
  });

  it("should create", () => {
    expect(component).toBeTruthy();
  });
  it("should render title", () => {
    expect(compiled.querySelector("h1")?.textContent).toBe(
      "Register new staff"
    );
  });

  it("should render Email address input", () => {
    fixture.detectChanges();
    const labels = Array.from(compiled.querySelectorAll("label")).map(
      ({ textContent }) => textContent
    );
    expect(labels).toContain("Email address");
  });

  it("should render Password input", () => {
    fixture.detectChanges();
    const labels = Array.from(compiled.querySelectorAll("label")).map(
      ({ textContent }) => textContent
    );
    expect(labels).toContain("Password");
  });
  it("should render Name input", () => {
    fixture.detectChanges();
    const labels = Array.from(compiled.querySelectorAll("label")).map(
      ({ textContent }) => textContent
    );
    expect(labels).toContain("Name");
  });
  it("should render Mobile Number input", () => {
    fixture.detectChanges();
    const labels = Array.from(compiled.querySelectorAll("label")).map(
      ({ textContent }) => textContent
    );
    expect(labels).toContain("Mobile Number");
  });

  it("should render submit button", () => {
    expect(compiled.querySelector("button")?.textContent).toContain("Submit");
  });
  it("should call onSubmit(ngForm: NgForm) method on submit button click", () => {
    spyOn<AddStaffComponent, any>(component, "onSubmit");
    fixture.detectChanges();
    fixture.debugElement.nativeElement.querySelector("button").click();
    expect(component.onSubmit).toHaveBeenCalled();
  });

  it("should render table", () => {
    expect(compiled.querySelector("table")).toBeTruthy();
  });
  it("should render 5 columns in table", () => {
    const tableRows = compiled.querySelectorAll("th");
    expect(tableRows.length).toBe(5);
  });
  it("should render columns with proper heading in table", () => {
    const tableRows = compiled.querySelectorAll("th");
    expect(tableRows.length).toBe(5);
    expect(tableRows[0].innerHTML).toBe("ID");
    expect(tableRows[1].innerHTML).toBe("Username/Email");
    expect(tableRows[2].innerHTML).toBe("Role");
    expect(tableRows[3].innerHTML).toBe("Status");
    expect(tableRows[4].innerHTML).toBe("Action");
  });
});
