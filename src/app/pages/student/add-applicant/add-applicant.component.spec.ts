import { ComponentFixture, TestBed } from "@angular/core/testing";
import { HttpClientModule } from "@angular/common/http";
import { AddApplicantComponent } from "./add-applicant.component";
import { AuthService } from "src/app/services/auth.service";
import { ApiService } from "src/app/services/api.service";
import { CourseService } from "src/app/services/course.service";
import { FormsModule } from "@angular/forms";

describe("AddApplicantComponent", () => {
  let component: AddApplicantComponent;
  let fixture: ComponentFixture<AddApplicantComponent>;
  let compiled: HTMLElement;
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [AddApplicantComponent],
      providers: [AuthService, ApiService, CourseService],
      imports: [HttpClientModule, FormsModule],
    }).compileComponents();

    fixture = TestBed.createComponent(AddApplicantComponent);
    component = fixture.componentInstance;
    compiled = fixture.nativeElement as HTMLElement;
    fixture.detectChanges();
  });

  it("should create", () => {
    expect(component).toBeTruthy();
  });
  it("should render title", () => {
    expect(compiled.querySelector("h1")?.textContent).toBe("Apply for course");
  });

  it("should render Applicant Name input", () => {
    fixture.detectChanges();
    const labels = Array.from(compiled.querySelectorAll("label")).map(
      ({ textContent }) => textContent
    );
    expect(labels).toContain("Applicant Name");
  });

  it("should render Applicant Degree input", () => {
    fixture.detectChanges();
    const labels = Array.from(compiled.querySelectorAll("label")).map(
      ({ textContent }) => textContent
    );
    expect(labels).toContain("Applicant Degree");
  });

  it("should render Applicant Graduation Percent input", () => {
    fixture.detectChanges();
    const labels = Array.from(compiled.querySelectorAll("label")).map(
      ({ textContent }) => textContent
    );
    expect(labels).toContain("Applicant Graduation Percent");
  });
  it("should render Applicant Mobile Number input", () => {
    fixture.detectChanges();
    const labels = Array.from(compiled.querySelectorAll("label")).map(
      ({ textContent }) => textContent
    );
    expect(labels).toContain("Applicant Mobile Number");
  });
  it("should render Course input", () => {
    fixture.detectChanges();
    const labels = Array.from(compiled.querySelectorAll("label")).map(
      ({ textContent }) => textContent
    );
    expect(labels).toContain("Course");
  });
  it("should render submit button", () => {
    expect(compiled.querySelector("button")?.textContent).toContain("Submit");
  });

  it("should call onSubmit(ngForm: NgForm) method on submit button click", () => {
    spyOn<AddApplicantComponent, any>(component, "onSubmit");
    fixture.detectChanges();
    fixture.debugElement.nativeElement.querySelector("button").click();
    expect(component.onSubmit).toHaveBeenCalled();
  });
});
