import { ComponentFixture, TestBed } from "@angular/core/testing";
import { HttpClientModule } from "@angular/common/http";
import { ApplicationDetailsComponent } from "./application-details.component";
import { ApiService } from "src/app/services/api.service";
import { ApplicantService } from "src/app/services/applicant.service";
import { FormsModule } from "@angular/forms";

describe("ApplicationDetailsComponent", () => {
  let component: ApplicationDetailsComponent;
  let fixture: ComponentFixture<ApplicationDetailsComponent>;
  let compiled: HTMLElement;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ApplicationDetailsComponent],
      providers: [ApiService, ApplicantService],
      imports: [HttpClientModule, FormsModule],
    }).compileComponents();

    fixture = TestBed.createComponent(ApplicationDetailsComponent);
    component = fixture.componentInstance;
    compiled = fixture.nativeElement as HTMLElement;
    fixture.detectChanges();
  });

  it("should create", () => {
    expect(component).toBeTruthy();
  });
  it("should render title", () => {
    expect(compiled.querySelector("h1")?.textContent).toBe(
      "Applied Applications"
    );
  });
  it("should render table", () => {
    expect(compiled.querySelector("table")).toBeTruthy();
  });
  it("should render 9 columns in table", () => {
    const tableRows = compiled.querySelectorAll("th");
    expect(tableRows.length).toBe(9);
  });
  it("should render columns with proper heading in table", () => {
    const tableRows = compiled.querySelectorAll("th");
    expect(tableRows.length).toBe(9);
    expect(tableRows[0].innerHTML).toBe("Name");
    expect(tableRows[1].innerHTML).toBe("Mobile");
    expect(tableRows[2].innerHTML).toBe("Degree");
    expect(tableRows[3].innerHTML).toBe("Percent");
    expect(tableRows[4].innerHTML).toBe("Email");
    expect(tableRows[5].innerHTML).toBe("Admission Date");
    expect(tableRows[6].innerHTML).toBe("Admission Status");
    expect(tableRows[7].innerHTML).toBe("Course");
    expect(tableRows[8].innerHTML).toBe("Fees");
  });
});
