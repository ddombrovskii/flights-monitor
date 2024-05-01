import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {Location} from "@angular/common";
import {PilotService} from "../service/pilot.service";
import {Pilot} from "../entity/pilot";

@Component({
  selector: 'app-pilot-edit',
  templateUrl: './pilot-edit.component.html',
  styleUrl: './pilot-edit.component.css'
})
export class PilotEditComponent implements OnInit {
  pilot: Pilot = {} as Pilot;
  edit: boolean = this.route.snapshot.paramMap.has('pilotId');

  constructor(private route: ActivatedRoute,
              private location: Location,
              private pilotService: PilotService) {
  }

  ngOnInit() {
    if (this.edit)
      this.getPilot();
  }

  getPilot(): void {
    const pilotId: number = parseInt(this.route.snapshot.paramMap.get('pilotId')!, 10);
    this.pilotService.readPilot(pilotId)
      .subscribe(pilot => this.pilot = pilot);
  }

  goBack(): void {
    this.location.back();
  }

  update(): void {
    if (this.pilot)
      this.pilotService.updatePilot(this.pilot).subscribe(() => this.goBack());
  }

  save(): void {
    if (this.pilot)
      this.pilotService.createPilot(this.pilot).subscribe(() => this.goBack());
  }
}
