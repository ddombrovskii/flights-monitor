import {Component, OnInit} from '@angular/core';
import {Violation} from "../entity/violation";
import {ViolationService} from "../service/violation.service";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-violations-list',
  templateUrl: './violations-list.component.html',
  styleUrl: './violations-list.component.css'
})
export class ViolationsListComponent implements OnInit {
  violations: Violation[] = [];
  flightId: number = parseInt(this.route.snapshot.paramMap.get('flightId')!, 10);

  constructor(private route: ActivatedRoute,
              private violationService: ViolationService) {
  }

  ngOnInit() {
    this.getViolations();
  }

  getViolations(): void {
    this.violationService.readAllViolations(this.flightId)
      .subscribe(violations => this.violations = violations)
  }

  deleteViolation(violation: Violation): void {
    this.violations = this.violations.filter(p => p !== violation);
    this.violationService.deleteViolation(this.flightId, violation.id).subscribe();
  }

  toDate(t: string): Date {
    let time: string = t || '00:00:00';
    return new Date(`01-01-00 ${time}`);
  }
}
