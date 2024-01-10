import { ComponentFixture, TestBed } from "@angular/core/testing";
import { HttpClientModule } from "@angular/common/http";
import { RegisterComponent } from "./register.component";
import { ApiService } from "src/app/services/api.service";
import { AuthService } from "src/app/services/auth.service";
import { FormsModule } from "@angular/forms";

describe("RegisterComponent", () => {
  let component: RegisterComponent;
  let fixture: ComponentFixture<RegisterComponent>;
  let compiled: HTMLElement;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [RegisterComponent],
      providers: [ApiService, AuthService],
      imports: [HttpClientModule, FormsModule],
    }).compileComponents();

    fixture = TestBed.createComponent(RegisterComponent);
    component = fixture.componentInstance;
    compiled = fixture.nativeElement as HTMLElement;
    fixture.detectChanges();
  });

  it("should create", () => {
    expect(component).toBeTruthy();
  });
  it("should render title", () => {
    expect(compiled.querySelector("h1")?.textContent).toBe("Register Here");
  });

  it("should render Email address input", () => {
    fixture.detectChanges();
    const labels = Array.from(compiled.querySelectorAll("label")).map(
      ({ textContent }) => textContent
    );
    expect(labels).toContain("Email address");
  });
  it("should render Role input", () => {
    fixture.detectChanges();
    const labels = Array.from(compiled.querySelectorAll("label")).map(
      ({ textContent }) => textContent
    );
    expect(labels).toContain("Role");
  });
  it("should render Password input", () => {
    fixture.detectChanges();
    const labels = Array.from(compiled.querySelectorAll("label")).map(
      ({ textContent }) => textContent
    );
    expect(labels).toContain("Password");
  });
  it("should render Name input", () => {
    fixture.detectChanges();
    const labels = Array.from(compiled.querySelectorAll("label")).map(
      ({ textContent }) => textContent
    );
    expect(labels).toContain("Name");
  });
  it("should render Mobile Number input", () => {
    fixture.detectChanges();
    const labels = Array.from(compiled.querySelectorAll("label")).map(
      ({ textContent }) => textContent
    );
    expect(labels).toContain("Mobile Number");
  });

  it("should render Admin Name based on condition", () => {
    const labelsBefore = Array.from(compiled.querySelectorAll("label")).map(
      ({ textContent }) => textContent
    );
    const roleSelector: HTMLSelectElement = compiled.querySelector(
      "select"
    ) as HTMLSelectElement;
    roleSelector.value = "ACM";
    roleSelector.dispatchEvent(new Event("change"));
    fixture.detectChanges();
    const labelsAfter = Array.from(compiled.querySelectorAll("label")).map(
      ({ textContent }) => textContent
    );
    expect(labelsBefore).not.toContain("Admin Name");
    expect(labelsAfter).toContain("Admin Name");
  });
  it("should render Admin Contact based on condition", () => {
    const labelsBefore = Array.from(compiled.querySelectorAll("label")).map(
      ({ textContent }) => textContent
    );
    const roleSelector: HTMLSelectElement = compiled.querySelector(
      "select"
    ) as HTMLSelectElement;
    roleSelector.value = "ACM";
    roleSelector.dispatchEvent(new Event("change"));
    fixture.detectChanges();
    const labelsAfter = Array.from(compiled.querySelectorAll("label")).map(
      ({ textContent }) => textContent
    );
    expect(labelsBefore).not.toContain("Admin Contact");
    expect(labelsAfter).toContain("Admin Contact");
  });
  it("should render submit button", () => {
    expect(compiled.querySelector("button")?.textContent).toContain("Submit");
  });
  it("should call onSubmit(ngForm: NgForm) method on submit button click", () => {
    spyOn<RegisterComponent, any>(component, "onSubmit");
    fixture.detectChanges();
    fixture.debugElement.nativeElement.querySelector("button").click();
    expect(component.onSubmit).toHaveBeenCalled();
  });
});
