import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Flight} from "../entity/flight";
import {Observable} from "rxjs";
import {environment} from "../../environments/environment.development";
import {Pilot} from "../entity/pilot";

@Injectable({
  providedIn: 'root'
})
export class FlightService {

  httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json'})
  };

  constructor(private http: HttpClient) {
  }

  createFlight(flight: Flight): Observable<Flight> {
    return this.http.post<Flight>(environment.apiUrl + '/flights', flight, this.httpOptions);
  }

  readFlight(id: number): Observable<Flight> {
    return this.http.get<Flight>(environment.apiUrl + `/flights/${id}`);
  }

  readAllFlights(): Observable<Flight[]> {
    return this.http.get<Flight[]>(environment.apiUrl + '/flights');
  }

  updateFlight(flight: Flight): Observable<Flight> {
    return this.http.put<Flight>(environment.apiUrl + `/flights/${flight.id}`, flight, this.httpOptions);
  }

  deleteFlight(id: number): Observable<void> {
    return this.http.delete<void>(environment.apiUrl + `/flights/${id}`);
  }

  getFlightPilots(): Observable<Record<number, Pilot[]>> {
    return this.http.get<Record<number, Pilot[]>>(environment.apiUrl + `/flights/pilots`);
  }

  addPilot(flightId: number, pilotId: number): Observable<void> {
    return this.http.put<void>(environment.apiUrl + `/flights/${flightId}/add-pilot/${pilotId}`, this.httpOptions);
  }

}
