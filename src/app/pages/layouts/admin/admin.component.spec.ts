import { ComponentFixture, TestBed } from "@angular/core/testing";
import { HttpClientModule } from "@angular/common/http";
import { AdminComponent } from "./admin.component";
import { ApiService } from "src/app/services/api.service";
import { AuthService } from "src/app/services/auth.service";
import { FormsModule } from "@angular/forms";
import { AppRoutingModule } from "src/app/app-routing.module";
describe("AdminComponent", () => {
  let component: AdminComponent;
  let fixture: ComponentFixture<AdminComponent>;
  let compiled: HTMLElement;
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [AdminComponent],
      providers: [ApiService, AuthService],
      imports: [HttpClientModule, FormsModule, AppRoutingModule],
    }).compileComponents();

    fixture = TestBed.createComponent(AdminComponent);
    component = fixture.componentInstance;
    compiled = fixture.nativeElement as HTMLElement;
    fixture.detectChanges();
  });
  // beforeAll(() => {
  //   window.onbeforeunload = () => "Prevent reload";
  // });

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
  it("should render navbar Register Staff item", () => {
    const labels = Array.from(compiled.querySelectorAll("a")).map(
      ({ textContent }) => textContent
    );
    expect(labels).toContain("Register Staff");
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
  // it("should call onLogout() method on logout button click", () => {
  //   spyOn<AdminComponent, any>(component, "onLogout");
  //   fixture.detectChanges();
  //   fixture.debugElement.nativeElement.querySelector("#logoutBtn").click();
  //   expect(component.onLogout).toHaveBeenCalled();
  // });
});
