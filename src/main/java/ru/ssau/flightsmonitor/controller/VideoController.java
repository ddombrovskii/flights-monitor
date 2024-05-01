package ru.ssau.flightsmonitor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ssau.flightsmonitor.exception.NoEntityException;
import ru.ssau.flightsmonitor.pojo.VideoPojo;
import ru.ssau.flightsmonitor.service.VideoService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/flights/{flightId}/video")
public class VideoController {
    @Autowired
    private VideoService videoService;

    // create POST
    @PostMapping
    public ResponseEntity<VideoPojo> createVideo(@PathVariable Long flightId,
                                                 @RequestBody VideoPojo pojo) {
        try {
            return new ResponseEntity<>(videoService.createVideo(flightId, pojo), HttpStatus.CREATED);
        } catch (NoEntityException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // read GET
    @GetMapping
    public ResponseEntity<VideoPojo> readVideo(@PathVariable Long flightId) {
        try {
            return new ResponseEntity<>(videoService.readVideo(flightId), HttpStatus.OK);
        } catch (NoEntityException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // update PUT
    @PutMapping
    public ResponseEntity<VideoPojo> updateVideo(@PathVariable Long flightId,
                                                 @RequestBody VideoPojo pojo) {
        try {
            return new ResponseEntity<>(videoService.updateVideo(flightId, pojo), HttpStatus.OK);
        } catch (NoEntityException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // delete DELETE
    @DeleteMapping("/{videoId}")
    public ResponseEntity<?> deleteVideo(@PathVariable Long flightId,
                                         @PathVariable Long videoId) {
        try {
            videoService.deleteVideo(flightId, videoId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoEntityException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
