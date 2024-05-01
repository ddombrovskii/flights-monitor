package ru.ssau.flightsmonitor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ssau.flightsmonitor.exception.NoEntityException;
import ru.ssau.flightsmonitor.pojo.FlightPojo;
import ru.ssau.flightsmonitor.pojo.PilotPojo;
import ru.ssau.flightsmonitor.service.FlightService;

import java.util.HashMap;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/flights")
public class FlightController {
    @Autowired
    private FlightService flightService;

    // read All GET
    @GetMapping()
    public ResponseEntity<List<FlightPojo>> readAllFlights() {
        return new ResponseEntity<>(flightService.readAllFlights(), HttpStatus.OK);
    }

    // create POST
    @PostMapping
    public ResponseEntity<FlightPojo> createFlight(@RequestBody FlightPojo pojo) {
        return new ResponseEntity<>(flightService.createFlight(pojo), HttpStatus.CREATED);
    }

    // read GET
    @GetMapping("/{flightId}")
    public ResponseEntity<FlightPojo> readFlight(@PathVariable Long flightId) {
        try {
            return new ResponseEntity<>(flightService.readFlight(flightId), HttpStatus.OK);
        } catch (NoEntityException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // update PUT
    @PutMapping("/{flightId}")
    public ResponseEntity<FlightPojo> updateFlight(@PathVariable Long flightId, @RequestBody FlightPojo pojo) {
        try {
            return new ResponseEntity<>(flightService.updateFlight(flightId, pojo), HttpStatus.OK);
        } catch (NoEntityException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // delete DELETE
    @DeleteMapping("/{flightId}")
    public ResponseEntity<?> deleteFlight(@PathVariable Long flightId) {
        try {
            flightService.deleteFlight(flightId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoEntityException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // add exists pilot to flight PUT
    @PutMapping("/{flightId}/add-pilot/{pilotId}")
    public ResponseEntity<PilotPojo> addPilotToFlight(@PathVariable Long flightId, @PathVariable Long pilotId) {
        try {
            flightService.addPilotToFlight(flightId, pilotId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoEntityException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // add new pilot to flight POST
    @PostMapping("/{flightId}/add-pilot/new")
    public ResponseEntity<PilotPojo> addNewPilotToFlight(@PathVariable Long flightId, @RequestBody PilotPojo pojo) {
        try {
            return new ResponseEntity<>(flightService.addNewPilotToFlight(flightId, pojo), HttpStatus.CREATED);
        } catch (NoEntityException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // get pilots of each flight GET
    @GetMapping("/pilots")
    public ResponseEntity<HashMap<Long, List<PilotPojo>>> getFlightPilots() {
        return new ResponseEntity<>(flightService.getFlightPilots(), HttpStatus.OK);
    }
}
