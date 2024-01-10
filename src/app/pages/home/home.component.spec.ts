import { ComponentFixture, TestBed } from "@angular/core/testing";
import { ApiService } from "src/app/services/api.service";
import { AuthService } from "src/app/services/auth.service";
import { CourseService } from "src/app/services/course.service";
import { HttpClientModule } from "@angular/common/http";
import { HomeComponent } from "./home.component";
import { FormsModule } from "@angular/forms";

describe("HomeComponent", () => {
  let component: HomeComponent;
  let fixture: ComponentFixture<HomeComponent>;
  let compiled: HTMLElement;
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [HomeComponent],
      providers: [AuthService, ApiService, CourseService],
      imports: [HttpClientModule, FormsModule],
    }).compileComponents();

    fixture = TestBed.createComponent(HomeComponent);
    component = fixture.componentInstance;
    compiled = fixture.nativeElement as HTMLElement;
    fixture.detectChanges();
  });

  it("should create", () => {
    expect(component).toBeTruthy();
  });

  it("should render container", () => {
    expect(compiled.querySelector(".container")).toBeTruthy();
  });
  it("should render page title", () => {
    expect(compiled.querySelector("h1")?.textContent).toBe("Cources list");
  });
});
