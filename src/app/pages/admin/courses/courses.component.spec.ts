import { ComponentFixture, TestBed } from "@angular/core/testing";
import { ApiService } from "src/app/services/api.service";
import { AuthService } from "src/app/services/auth.service";
import { HttpClientModule } from "@angular/common/http";
import { CoursesComponent } from "./courses.component";
import { FormsModule } from "@angular/forms";
import { CourseService } from "src/app/services/course.service";

describe("CoursesComponent", () => {
  let component: CoursesComponent;
  let fixture: ComponentFixture<CoursesComponent>;
  let compiled: HTMLElement;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [CoursesComponent],
      providers: [AuthService, ApiService, CourseService],
      imports: [HttpClientModule, FormsModule],
    }).compileComponents();

    fixture = TestBed.createComponent(CoursesComponent);
    component = fixture.componentInstance;
    compiled = fixture.nativeElement as HTMLElement;
    fixture.detectChanges();
  });

  it("should create", () => {
    expect(component).toBeTruthy();
  });
  it("should render title", () => {
    expect(compiled.querySelector("h1")?.textContent).toBe(
      "Register new courses"
    );
  });

  it("should render Course Name input", () => {
    fixture.detectChanges();
    const labels = Array.from(compiled.querySelectorAll("label")).map(
      ({ textContent }) => textContent
    );
    expect(labels).toContain("Course Name");
  });
  it("should render Course Fee input", () => {
    fixture.detectChanges();
    const labels = Array.from(compiled.querySelectorAll("label")).map(
      ({ textContent }) => textContent
    );
    expect(labels).toContain("Course Fee");
  });
  it("should render Course Start Date input", () => {
    fixture.detectChanges();
    const labels = Array.from(compiled.querySelectorAll("label")).map(
      ({ textContent }) => textContent
    );
    expect(labels).toContain("Course Start Date");
  });
  it("should render Course End Date input", () => {
    fixture.detectChanges();
    const labels = Array.from(compiled.querySelectorAll("label")).map(
      ({ textContent }) => textContent
    );
    expect(labels).toContain("Course End Date");
  });
  it("should render Course Duration (In Month) input", () => {
    fixture.detectChanges();
    const labels = Array.from(compiled.querySelectorAll("label")).map(
      ({ textContent }) => textContent
    );
    expect(labels).toContain("Course Duration (In Month)");
  });
  it("should render submit button", () => {
    expect(compiled.querySelector("button")?.textContent).toContain("Submit");
  });
  it("should call onSubmit(ngForm: NgForm) method on submit button click", () => {
    spyOn<CoursesComponent, any>(component, "onSubmit");
    fixture.detectChanges();
    fixture.debugElement.nativeElement.querySelector("button").click();
    expect(component.onSubmit).toHaveBeenCalled();
  });

  it("should render table", () => {
    expect(compiled.querySelector("table")).toBeTruthy();
  });
  it("should render 7 columns in table", () => {
    const tableRows = compiled.querySelectorAll("th");
    expect(tableRows.length).toBe(7);
  });

  it("should render columns with proper heading in table", () => {
    const tableRows = compiled.querySelectorAll("th");
    expect(tableRows.length).toBe(7);
    expect(tableRows[0].innerHTML).toBe("ID");
    expect(tableRows[1].innerHTML).toBe("Name");
    expect(tableRows[2].innerHTML).toBe("Start Date");
    expect(tableRows[3].innerHTML).toBe("End Date");
    expect(tableRows[4].innerHTML).toBe("Duration (Month)");
    expect(tableRows[5].innerHTML).toBe("Fees");
    expect(tableRows[6].innerHTML).toBe("Action");
  });
});
