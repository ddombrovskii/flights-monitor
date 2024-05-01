import {Component, OnInit} from '@angular/core';
import {PilotService} from "../service/pilot.service";
import {Pilot} from "../entity/pilot";
import {forkJoin} from "rxjs";

@Component({
  selector: 'app-pilots-list',
  templateUrl: './pilots-list.component.html',
  styleUrl: './pilots-list.component.css'
})
export class PilotsListComponent implements OnInit {
  pilots: Pilot[] = [];
  countFlights: Record<number, number> = {} as Record<number, number>;

  constructor(private pilotService: PilotService) {
  }

  ngOnInit() {
    this.getPilots();
  }

  getPilots(): void {
    forkJoin([this.pilotService.readAllPilots(), this.pilotService.getCountOfFlights()])
      .subscribe(res => {
        this.pilots = res[0];
        this.countFlights = res[1];
      })
  }

  deletePilot(pilot: Pilot): void {
    this.pilots = this.pilots.filter(p => p !== pilot);
    this.pilotService.deletePilot(pilot.id).subscribe();
  }
}
