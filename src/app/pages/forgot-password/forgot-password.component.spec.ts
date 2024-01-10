import { ComponentFixture, TestBed } from "@angular/core/testing";
import { ApiService } from "src/app/services/api.service";
import { AuthService } from "src/app/services/auth.service";
import { HttpClientModule } from "@angular/common/http";
import { ForgotPasswordComponent } from "./forgot-password.component";
import { FormsModule } from "@angular/forms";

describe("ForgotPasswordComponent", () => {
  let component: ForgotPasswordComponent;
  let fixture: ComponentFixture<ForgotPasswordComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ForgotPasswordComponent],
      providers: [AuthService, ApiService],
      imports: [HttpClientModule, FormsModule],
    }).compileComponents();

    fixture = TestBed.createComponent(ForgotPasswordComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it("should create", () => {
    expect(component).toBeTruthy();
  });

  it("should render title", () => {
    const fixture = TestBed.createComponent(ForgotPasswordComponent);
    fixture.detectChanges();
    const compiled = fixture.nativeElement as HTMLElement;
    expect(compiled.querySelector("h1")?.textContent).toContain(
      "Change Password"
    );
  });
  it("should render Email address input", () => {
    const fixture = TestBed.createComponent(ForgotPasswordComponent);
    fixture.detectChanges();
    const compiled = fixture.nativeElement as HTMLElement;
    const labels = Array.from(compiled.querySelectorAll("label")).map(
      ({ textContent }) => textContent
    );
    expect(labels).toContain("Email address");
  });
  it("should render Role input", () => {
    const fixture = TestBed.createComponent(ForgotPasswordComponent);
    fixture.detectChanges();
    const compiled = fixture.nativeElement as HTMLElement;
    const labels = Array.from(compiled.querySelectorAll("label")).map(
      ({ textContent }) => textContent
    );
    expect(labels).toContain("Role");
  });
  it("should render Old Password input", () => {
    const fixture = TestBed.createComponent(ForgotPasswordComponent);
    fixture.detectChanges();
    const compiled = fixture.nativeElement as HTMLElement;
    const labels = Array.from(compiled.querySelectorAll("label")).map(
      ({ textContent }) => textContent
    );
    expect(labels).toContain("Old Password");
  });
  it("should render New Password input", () => {
    const fixture = TestBed.createComponent(ForgotPasswordComponent);
    fixture.detectChanges();
    const compiled = fixture.nativeElement as HTMLElement;
    const labels = Array.from(compiled.querySelectorAll("label")).map(
      ({ textContent }) => textContent
    );
    expect(labels).toContain("New Password");
  });

  it("should render submit button", () => {
    const fixture = TestBed.createComponent(ForgotPasswordComponent);
    fixture.detectChanges();
    const compiled = fixture.nativeElement as HTMLElement;
    expect(compiled.querySelector("button")?.textContent).toContain("Submit");
  });

  it("should call onSubmit(ngForm: NgForm) method on submit button click", () => {
    const fixture = TestBed.createComponent(ForgotPasswordComponent);
    const comp = fixture.componentInstance;
    spyOn<ForgotPasswordComponent, any>(comp, "onSubmit");
    fixture.detectChanges();
    const button = fixture.debugElement.nativeElement.querySelector("button");
    button.click();
    expect(comp.onSubmit).toHaveBeenCalled();
  });
});
