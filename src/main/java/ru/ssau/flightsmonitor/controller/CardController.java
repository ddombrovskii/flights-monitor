package ru.ssau.flightsmonitor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ssau.flightsmonitor.exception.NoEntityException;
import ru.ssau.flightsmonitor.pojo.CardPojo;
import ru.ssau.flightsmonitor.service.CardService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/flights/{flightId}/violations/{violationId}/card")
public class CardController {
    @Autowired
    private CardService cardService;

    // create POST
    @PostMapping
    public ResponseEntity<CardPojo> createCard(@PathVariable Long flightId,
                                               @PathVariable Long violationId,
                                               @RequestBody CardPojo pojo) {
        try {
            return new ResponseEntity<>(cardService.createCard(flightId, violationId, pojo), HttpStatus.CREATED);
        } catch (NoEntityException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // read GET
    @GetMapping("/{cardId}")
    public ResponseEntity<CardPojo> readCard(@PathVariable Long flightId,
                                             @PathVariable Long violationId,
                                             @PathVariable Long cardId) {
        try {
            return new ResponseEntity<>(cardService.readCard(flightId, violationId, cardId), HttpStatus.OK);
        } catch (NoEntityException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // update PUT
    @PutMapping("/{cardId}")
    public ResponseEntity<CardPojo> updateCard(@PathVariable Long flightId,
                                               @PathVariable Long violationId,
                                               @PathVariable Long cardId,
                                               @RequestBody CardPojo pojo) {
        try {
            return new ResponseEntity<>(cardService.updateCard(flightId, violationId, cardId, pojo), HttpStatus.OK);
        } catch (NoEntityException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // delete DELETE
    @DeleteMapping("/{cardId}")
    public ResponseEntity<?> deleteCard(@PathVariable Long flightId,
                                        @PathVariable Long violationId,
                                        @PathVariable Long cardId) {
        try {
            cardService.deleteCard(flightId, violationId, cardId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoEntityException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
