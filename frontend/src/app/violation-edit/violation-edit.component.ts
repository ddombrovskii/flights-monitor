import {Component, OnInit} from '@angular/core';
import {Violation} from "../entity/violation";
import {ActivatedRoute} from "@angular/router";
import {Location} from "@angular/common";
import {ViolationService} from "../service/violation.service";

@Component({
  selector: 'app-violation-edit',
  templateUrl: './violation-edit.component.html',
  styleUrl: './violation-edit.component.css'
})
export class ViolationEditComponent implements OnInit {
  edit: boolean = this.route.snapshot.paramMap.has('violationId');
  flightId: number = parseInt(this.route.snapshot.paramMap.get('flightId')!, 10);
  violation: Violation = {} as Violation;

  constructor(private route: ActivatedRoute,
              private location: Location,
              private violationService: ViolationService) {
  }

  ngOnInit() {
    if (this.edit)
      this.getViolation();
  }

  getViolation(): void {
    const violationId: number = parseInt(this.route.snapshot.paramMap.get('violationId')!, 10);
    this.violationService.readViolation(this.flightId, violationId)
      .subscribe(violation => this.violation = violation);
  }

  goBack(): void {
    this.location.back();
  }

  update(): void {
    if (this.violation)
      this.violationService.updateViolation(this.flightId, this.violation)
        .subscribe(() => this.goBack());
  }

  save(): void {
    if (this.violation)
      this.violationService.createViolation(this.flightId, this.violation)
        .subscribe(() => this.goBack());
  }
}
