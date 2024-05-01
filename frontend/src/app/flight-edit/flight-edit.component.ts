import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {Location} from '@angular/common';

import {FlightService} from "../service/flight.service";
import {Flight} from "../entity/flight";
import {Pilot} from "../entity/pilot";
import {forkJoin} from "rxjs";
import {PilotService} from "../service/pilot.service";
import {VideoService} from "../service/video.service";
import {Video} from "../entity/video";

@Component({
  selector: 'app-flight-edit',
  templateUrl: './flight-edit.component.html',
  styleUrl: './flight-edit.component.css'
})
export class FlightEditComponent implements OnInit {

  edit: boolean = this.route.snapshot.paramMap.has('flightId');
  flight: Flight = {} as Flight;
  @Input() flightPilots: Pilot[] = [];
  allPilots: Pilot[] = [];
  @Input() availablePilots: Pilot[] = [];
  @Input() selectedPilot: Pilot = {} as Pilot;

  @Output() flightPilotsChange = new EventEmitter<Pilot[]>;
  @Output() availablePilotsChange = new EventEmitter<Pilot[]>;
  @Output() selectedPilotChange = new EventEmitter<Pilot>;

  video: Video = {} as Video;
  @Input() videoAdded: boolean = false;
  @Output() videoAddedChange = new EventEmitter<boolean>;

  constructor(private route: ActivatedRoute,
              private location: Location,
              private flightService: FlightService,
              private pilotService: PilotService,
              private videoService: VideoService) {
  }

  ngOnInit() {
    if (this.edit)
      this.getFlight();
  }

  getFlight(): void {
    const flightId: number = parseInt(this.route.snapshot.paramMap.get('flightId')!, 10);
    forkJoin([this.flightService.readFlight(flightId),
      this.flightService.getFlightPilots(),
      this.pilotService.readAllPilots()
    ])
      .subscribe(res => {
        this.flight = res[0];
        this.flightPilots = res[1][flightId];
        this.allPilots = res[2];
        this.availablePilots = this.allPilots
          .filter(item1 => !(this.flightPilots.some(item2 => item2.id === item1.id)))
        this.selectedPilot = this.availablePilots[0];
      })
  }

  addPilot(pilot: Pilot): void {
    this.flightService.addPilot(this.flight.id, pilot.id).subscribe();

    const index = this.availablePilots.indexOf(pilot);
    this.availablePilots.splice(index, 1);

    this.flightPilots.push(pilot);
    this.selectedPilot = this.availablePilots[0];

    this.flightPilotsChange.emit(this.flightPilots);
    this.availablePilotsChange.emit(this.availablePilots);
    this.selectedPilotChange.emit(this.selectedPilot);
  }

  addVideo(video: Video): void {
    this.videoService.createVideo(this.flight.id, video).subscribe();
    this.videoAdded = true;
    this.videoAddedChange.emit(this.videoAdded);
  }

  goBack(): void {
    this.location.back();
  }

  update(): void {
    if (this.flight)
      this.flightService.updateFlight(this.flight).subscribe(() => this.goBack());
  }

  save(): void {
    if (this.flight)
      this.flightService.createFlight(this.flight).subscribe(() => this.goBack());
  }
}
