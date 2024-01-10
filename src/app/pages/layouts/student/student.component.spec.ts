import { ComponentFixture, TestBed } from "@angular/core/testing";
import { FormsModule } from "@angular/forms";
import { AppRoutingModule } from "src/app/app-routing.module";

import { StudentComponent } from "./student.component";

describe("StudentComponent", () => {
  let component: StudentComponent;
  let fixture: ComponentFixture<StudentComponent>;
  let compiled: HTMLElement;
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [StudentComponent],
      imports: [FormsModule, AppRoutingModule],
    }).compileComponents();

    fixture = TestBed.createComponent(StudentComponent);
    component = fixture.componentInstance;
    compiled = fixture.nativeElement as HTMLElement;
    fixture.detectChanges();
  });

  it("should create", () => {
    expect(component).toBeTruthy();
  });
  it("should render navbar branding", () => {
    expect(compiled.querySelector(".navbar-brand")?.textContent).toBe(
      "University System"
    );
  });
  it("should render navbar Application Details item", () => {
    const labels = Array.from(compiled.querySelectorAll("a")).map(
      ({ textContent }) => textContent
    );
    expect(labels).toContain("Application Details");
  });
  it("should render navbar Add Applicant item", () => {
    const labels = Array.from(compiled.querySelectorAll("a")).map(
      ({ textContent }) => textContent
    );
    expect(labels).toContain("Add Applicant");
  });
  it("should render navbar Logout item", () => {
    const labels = Array.from(compiled.querySelectorAll("a")).map(
      ({ textContent }) => textContent
    );
    expect(labels).toContain("Logout");
  });
});
