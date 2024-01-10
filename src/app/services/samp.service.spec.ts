import { TestBed } from "@angular/core/testing";

import { SampService } from "./samp.service";

describe("SampService", () => {
  let service: SampService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SampService);
  });

  it("should be created", () => {
    expect(service).toBeTruthy();
  });
  it("should be return val", () => {
    expect(service.getAdd(2)).toBe(4);
  });
});
