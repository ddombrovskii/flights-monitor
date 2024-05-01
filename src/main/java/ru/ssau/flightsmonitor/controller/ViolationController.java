package ru.ssau.flightsmonitor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ssau.flightsmonitor.exception.NoEntityException;
import ru.ssau.flightsmonitor.pojo.ViolationPojo;
import ru.ssau.flightsmonitor.service.ViolationService;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/flights/{flightId}/violations")
public class ViolationController {
    @Autowired
    private ViolationService violationService;

    // create POST
    @PostMapping
    public ResponseEntity<ViolationPojo> createViolation(@PathVariable Long flightId,
                                                         @RequestBody ViolationPojo pojo) {
        try {
            return new ResponseEntity<>(violationService.createViolation(flightId, pojo), HttpStatus.CREATED);
        } catch (NoEntityException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // read GET
    @GetMapping("/{violationId}")
    public ResponseEntity<ViolationPojo> readViolation(@PathVariable Long flightId,
                                                       @PathVariable Long violationId) {
        try {
            return new ResponseEntity<>(violationService.readViolation(flightId, violationId), HttpStatus.OK);
        } catch (NoEntityException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    // read all GET
    @GetMapping
    public ResponseEntity<List<ViolationPojo>> readAllViolations(@PathVariable Long flightId) {
        try {
            return new ResponseEntity<>(violationService.readAllViolations(flightId), HttpStatus.OK);
        } catch (NoEntityException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // update PUT
    @PutMapping("/{violationId}")
    public ResponseEntity<ViolationPojo> updateViolation(@PathVariable Long flightId,
                                                         @PathVariable Long violationId,
                                                         @RequestBody ViolationPojo pojo) {
        try {
            return new ResponseEntity<>(violationService.updateViolation(flightId, violationId, pojo), HttpStatus.OK);
        } catch (NoEntityException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // delete DELETE
    @DeleteMapping("/{violationId}")
    public ResponseEntity<?> deleteViolation(@PathVariable Long flightId,
                                             @PathVariable Long violationId) {
        try {
            violationService.deleteViolation(flightId, violationId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoEntityException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
