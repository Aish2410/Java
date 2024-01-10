import { ComponentFixture, TestBed } from "@angular/core/testing";
import { ApiService } from "src/app/services/api.service";
import { AuthService } from "src/app/services/auth.service";
import { HttpClientModule } from "@angular/common/http";
import { LoginComponent } from "./login.component";
import { FormsModule } from "@angular/forms";

describe("LoginComponent", () => {
  let component: LoginComponent;
  let fixture: ComponentFixture<LoginComponent>;
  let _fixture: ComponentFixture<LoginComponent>;
  let _compiled: HTMLElement;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [LoginComponent],
      providers: [AuthService, ApiService],
      imports: [HttpClientModule, FormsModule],
    }).compileComponents();

    fixture = TestBed.createComponent(LoginComponent);
    _fixture = fixture;
    _compiled = fixture.nativeElement as HTMLElement;
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it("should create", () => {
    expect(component).toBeTruthy();
  });
  it("should render title", () => {
    expect(_compiled.querySelector("h1")?.textContent).toBe("Login Please..");
  });

  it("should render Email address input", () => {
    _fixture.detectChanges();
    const labels = Array.from(_compiled.querySelectorAll("label")).map(
      ({ textContent }) => textContent
    );
    expect(labels).toContain("Email address");
  });
  it("should render Role input", () => {
    _fixture.detectChanges();
    const labels = Array.from(_compiled.querySelectorAll("label")).map(
      ({ textContent }) => textContent
    );
    expect(labels).toContain("Role");
  });
  it("should render Password input", () => {
    _fixture.detectChanges();
    const labels = Array.from(_compiled.querySelectorAll("label")).map(
      ({ textContent }) => textContent
    );
    expect(labels).toContain("Password");
  });
  it("should call onSubmit(ngForm: NgForm) method on submit button click", () => {
    spyOn<LoginComponent, any>(component, "onSubmit");
    fixture.detectChanges();
    fixture.debugElement.nativeElement.querySelector("button").click();
    expect(component.onSubmit).toHaveBeenCalled();
  });
});
