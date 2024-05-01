import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {environment} from "../../environments/environment.development";
import {Violation} from "../entity/violation";

@Injectable({
  providedIn: 'root'
})
export class ViolationService {

  httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json'})
  };

  constructor(private http: HttpClient) { }

  createViolation(flightId: number, violation: Violation): Observable<Violation> {
    return this.http.post<Violation>(environment.apiUrl + `/flights/${flightId}/violations`, violation, this.httpOptions);
  }

  readViolation(flightId: number, violationId: number): Observable<Violation> {
    return this.http.get<Violation>(environment.apiUrl + `/flights/${flightId}/violations/${violationId}`);
  }

  readAllViolations(flightId: number): Observable<Violation[]> {
    return this.http.get<Violation[]>(environment.apiUrl + `/flights/${flightId}/violations`);
  }

  updateViolation(flightId: number, violation: Violation): Observable<Violation> {
    return this.http.put<Violation>(environment.apiUrl + `/flights/${flightId}/violations/${violation.id}`, violation, this.httpOptions);
  }

  deleteViolation(flightId: number, violationId: number): Observable<void> {
    return this.http.delete<void>(environment.apiUrl + `/flights/${flightId}/violations/${violationId}`);
  }
}
