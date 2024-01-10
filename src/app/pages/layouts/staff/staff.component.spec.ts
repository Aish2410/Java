import { ComponentFixture, TestBed } from "@angular/core/testing";
import { AppRoutingModule } from "src/app/app-routing.module";

import { StaffComponent } from "./staff.component";

describe("StaffComponent", () => {
  let component: StaffComponent;
  let fixture: ComponentFixture<StaffComponent>;
  let compiled: HTMLElement;
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [StaffComponent],
      imports: [AppRoutingModule],
    }).compileComponents();

    fixture = TestBed.createComponent(StaffComponent);
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
  it("should render navbar Courses item", () => {
    const labels = Array.from(compiled.querySelectorAll("a")).map(
      ({ textContent }) => textContent
    );
    expect(labels).toContain("Courses");
  });
  it("should render navbar Add Admission item", () => {
    const labels = Array.from(compiled.querySelectorAll("a")).map(
      ({ textContent }) => textContent
    );
    expect(labels).toContain("Add Admission");
  });
  it("should render navbar Logout item", () => {
    const labels = Array.from(compiled.querySelectorAll("a")).map(
      ({ textContent }) => textContent
    );
    expect(labels).toContain("Logout");
  });
});
