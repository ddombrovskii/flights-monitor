import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {environment} from "../../environments/environment.development";
import {Pilot} from "../entity/pilot";

@Injectable({
  providedIn: 'root'
})
export class PilotService {

  httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json'})
  };

  constructor(private http: HttpClient) {
  }

  createPilot(pilot: Pilot): Observable<Pilot> {
    return this.http.post<Pilot>(environment.apiUrl + '/pilots', pilot, this.httpOptions);
  }

  readPilot(id: number): Observable<Pilot> {
    return this.http.get<Pilot>(environment.apiUrl + `/pilots/${id}`);
  }

  readAllPilots(): Observable<Pilot[]> {
    return this.http.get<Pilot[]>(environment.apiUrl + '/pilots');
  }

  updatePilot(pilot: Pilot): Observable<Pilot> {
    return this.http.put<Pilot>(environment.apiUrl + `/pilots/${pilot.id}`, pilot, this.httpOptions);
  }

  deletePilot(id: number): Observable<void> {
    return this.http.delete<void>(environment.apiUrl + `/pilots/${id}`);
  }

  getCountOfFlights(): Observable<Record<number, number>> {
    return this.http.get<Record<number, number>>(environment.apiUrl + '/pilots/count-flights');
  }
}
