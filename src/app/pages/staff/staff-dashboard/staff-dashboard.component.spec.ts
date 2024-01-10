import { ComponentFixture, TestBed } from "@angular/core/testing";

import { StaffDashboardComponent } from "./staff-dashboard.component";

describe("StaffDashboardComponent", () => {
  let component: StaffDashboardComponent;
  let fixture: ComponentFixture<StaffDashboardComponent>;
  let compiled: HTMLElement;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [StaffDashboardComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(StaffDashboardComponent);
    component = fixture.componentInstance;
    compiled = fixture.nativeElement as HTMLElement;
    fixture.detectChanges();
  });

  it("should create", () => {
    expect(component).toBeTruthy();
  });
  it("should render welcome text", () => {
    expect(compiled.querySelector("h1")?.textContent).toBe(
      "Welcome to Staff Panel"
    );
  });
});
