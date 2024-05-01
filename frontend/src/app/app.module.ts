import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FlightsListComponent } from './flights-list/flights-list.component';
import {HttpClientModule} from "@angular/common/http";
import { FlightEditComponent } from './flight-edit/flight-edit.component';
import { ViolationEditComponent } from './violation-edit/violation-edit.component';
import { VideoComponent } from './video/video.component';
import { PilotsListComponent } from './pilots-list/pilots-list.component';
import { PilotEditComponent } from './pilot-edit/pilot-edit.component';
import { ViolationsListComponent } from './violations-list/violations-list.component';
import {FormsModule} from "@angular/forms";

@NgModule({
  declarations: [
    AppComponent,
    FlightsListComponent,
    FlightEditComponent,
    ViolationsListComponent,
    ViolationEditComponent,
    VideoComponent,
    PilotsListComponent,
    PilotEditComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
