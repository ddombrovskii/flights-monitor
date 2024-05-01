package ru.ssau.flightsmonitor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ssau.flightsmonitor.exception.NoEntityException;
import ru.ssau.flightsmonitor.pojo.FlightPojo;
import ru.ssau.flightsmonitor.pojo.PilotPojo;
import ru.ssau.flightsmonitor.service.FlightService;
import ru.ssau.flightsmonitor.service.PilotService;

import java.util.HashMap;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/pilots")
public class PilotController {
    @Autowired
    private PilotService pilotService;
    @Autowired
    private FlightService flightService;

    // read All GET
    @GetMapping
    public ResponseEntity<List<PilotPojo>> readAllPilots() {
        return new ResponseEntity<>(pilotService.readAllPilots(), HttpStatus.OK);
    }

    // create POST
    @PostMapping
    public ResponseEntity<PilotPojo> createPilot(@RequestBody PilotPojo pojo) {
        return new ResponseEntity<>(pilotService.createPilot(pojo), HttpStatus.CREATED);
    }

    // read GET
    @GetMapping("/{pilotId}")
    public ResponseEntity<PilotPojo> readPilot(@PathVariable Long pilotId) {
        try {
            return new ResponseEntity<>(pilotService.readPilot(pilotId), HttpStatus.OK);
        } catch (NoEntityException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // update PUT
    @PutMapping("/{pilotId}")
    public ResponseEntity<PilotPojo> updatePilot(@PathVariable Long pilotId, @RequestBody PilotPojo pojo) {
        try {
            return new ResponseEntity<>(pilotService.updatePilot(pilotId, pojo), HttpStatus.OK);
        } catch (NoEntityException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // delete DELETE
    @DeleteMapping("/{pilotId}")
    public ResponseEntity<?> deletePilot(@PathVariable Long pilotId) {
        try {
            pilotService.deletePilot(pilotId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoEntityException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // get flights count of each pilot GET
    @GetMapping("/count-flights")
    public ResponseEntity<HashMap<Long, Integer>> getCountOfFlights() {
        return new ResponseEntity<>(pilotService.getCountOfFlights(), HttpStatus.OK);
    }
}
