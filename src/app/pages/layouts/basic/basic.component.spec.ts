import { ComponentFixture, TestBed } from "@angular/core/testing";
import { HttpClientModule } from "@angular/common/http";
import { BasicComponent } from "./basic.component";
import { ApiService } from "src/app/services/api.service";
import { AuthService } from "src/app/services/auth.service";
import { FormsModule } from "@angular/forms";
import { AppRoutingModule } from "src/app/app-routing.module";
describe("BasicComponent", () => {
  let component: BasicComponent;
  let fixture: ComponentFixture<BasicComponent>;
  let compiled: HTMLElement;
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [BasicComponent],
      providers: [ApiService, AuthService],
      imports: [HttpClientModule, FormsModule, AppRoutingModule],
    }).compileComponents();

    fixture = TestBed.createComponent(BasicComponent);
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
  it("should render navbar Login item", () => {
    const labels = Array.from(compiled.querySelectorAll("a")).map(
      ({ textContent }) => textContent
    );
    expect(labels).toContain("Login");
  });
  it("should render navbar Register item", () => {
    const labels = Array.from(compiled.querySelectorAll("a")).map(
      ({ textContent }) => textContent
    );
    expect(labels).toContain("Register");
  });
  it("should render navbar Forgot Password item", () => {
    const labels = Array.from(compiled.querySelectorAll("a")).map(
      ({ textContent }) => textContent
    );
    expect(labels).toContain("Change Password");
  });
});
