import {Component, OnInit} from '@angular/core';
import {FlightService} from "../service/flight.service";
import {Flight} from "../entity/flight";
import {forkJoin, Observable} from "rxjs";
import {Pilot} from "../entity/pilot";
import {DomSanitizer} from "@angular/platform-browser";

@Component({
  selector: 'app-flights-list',
  templateUrl: './flights-list.component.html',
  styleUrl: './flights-list.component.css'
})
export class FlightsListComponent implements OnInit {
  flights: Flight[] = []
  flightPilots: Record<number, Pilot[]> = {} as Record<number, Pilot[]>;

  constructor(private flightService: FlightService,
              private sanitizer: DomSanitizer) {
  }

  widgetUrl(place: string) {
    const url = `https://yandex.ru/map-widget/v1/?ll=${place.split(',')[0]}%2C${place.split(',')[1]}`;
    return this.sanitizer.bypassSecurityTrustResourceUrl(url);
  }

  ngOnInit() {
    this.getFlights();
  }

  getFlights(): void {
    forkJoin([this.flightService.readAllFlights(),
      this.flightService.getFlightPilots()])
      .subscribe(res => {
        this.flights = res[0];
        this.flightPilots = res[1];
      })
  }

  deleteFlight(flight: Flight): void {
    this.flights = this.flights.filter(p => p !== flight);
    this.flightService.deleteFlight(flight.id).subscribe();
  }
}
