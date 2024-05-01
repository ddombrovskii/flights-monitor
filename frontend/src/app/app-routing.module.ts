import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {FlightsListComponent} from "./flights-list/flights-list.component";
import {FlightEditComponent} from "./flight-edit/flight-edit.component";
import {ViolationsListComponent} from "./violations-list/violations-list.component";
import {ViolationEditComponent} from "./violation-edit/violation-edit.component";
import {VideoComponent} from "./video/video.component";
import {PilotsListComponent} from "./pilots-list/pilots-list.component";
import {PilotEditComponent} from "./pilot-edit/pilot-edit.component";

const routes: Routes = [
  {path: '', redirectTo: 'flights', pathMatch: 'full'},
  {path: 'flights/new', component: FlightEditComponent},
  {path: 'flights/edit/:flightId', component: FlightEditComponent},
  {path: 'flights', component: FlightsListComponent},
  {
    path: 'flights/:flightId',
    children: [
      {path: 'violations', component: ViolationsListComponent},
      {path: 'violations/new', component: ViolationEditComponent},
      {path: 'violations/edit/:violationId', component: ViolationEditComponent},
      {path: 'video', component: VideoComponent}]
  },
  {path: 'pilots', component: PilotsListComponent},
  {path: 'pilots/new', component: PilotEditComponent},
  {path: 'pilots/edit/:pilotId', component: PilotEditComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
