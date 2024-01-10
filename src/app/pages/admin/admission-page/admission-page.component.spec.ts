import { ComponentFixture, TestBed } from "@angular/core/testing";
import { HttpClientModule } from "@angular/common/http";
import { AdmissionPageComponent } from "./admission-page.component";
import { ApiService } from "src/app/services/api.service";
import { CourseService } from "src/app/services/course.service";
import { FormsModule } from "@angular/forms";

describe("AdmissionPageComponent", () => {
  let component: AdmissionPageComponent;
  let fixture: ComponentFixture<AdmissionPageComponent>;
  let compiled: HTMLElement;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [AdmissionPageComponent],
      providers: [ApiService, CourseService],
      imports: [HttpClientModule, FormsModule],
    }).compileComponents();

    fixture = TestBed.createComponent(AdmissionPageComponent);
    component = fixture.componentInstance;
    compiled = fixture.nativeElement as HTMLElement;
    fixture.detectChanges();
  });

  it("should create", () => {
    expect(component).toBeTruthy();
  });

  it("should render title", () => {
    expect(compiled.querySelector("h1")?.textContent).toBe("Add Applicant");
  });

  it("should render Email address input", () => {
    fixture.detectChanges();
    const labels = Array.from(compiled.querySelectorAll("label")).map(
      ({ textContent }) => textContent
    );
    expect(labels).toContain("Email address");
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
    spyOn<AdmissionPageComponent, any>(component, "onSubmit");
    fixture.detectChanges();
    fixture.debugElement.nativeElement.querySelector("button").click();
    expect(component.onSubmit).toHaveBeenCalled();
  });

  it("should render table", () => {
    expect(compiled.querySelector("table")).toBeTruthy();
  });
  it("should render 10 columns in table", () => {
    const tableRows = compiled.querySelectorAll("th");
    expect(tableRows.length).toBe(10);
  });
  it("should render columns with proper heading in table", () => {
    const tableRows = compiled.querySelectorAll("th");
    expect(tableRows.length).toBe(10);
    expect(tableRows[0].innerHTML).toBe("Name");
    expect(tableRows[1].innerHTML).toBe("Mobile");
    expect(tableRows[2].innerHTML).toBe("Degree");
    expect(tableRows[3].innerHTML).toBe("Percent");
    expect(tableRows[4].innerHTML).toBe("Email");
    expect(tableRows[5].innerHTML).toBe("Admission Date");
    expect(tableRows[6].innerHTML).toBe("Admission Status");
    expect(tableRows[7].innerHTML).toBe("Course");
    expect(tableRows[8].innerHTML).toBe("Fees");
    expect(tableRows[9].innerHTML).toBe("Action");
  });
});
