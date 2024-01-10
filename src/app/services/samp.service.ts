import { Injectable } from "@angular/core";

@Injectable({
  providedIn: "root",
})
export class SampService {
  constructor() {}

  getAdd(num: number) {
    return num + 2;
  }
}
