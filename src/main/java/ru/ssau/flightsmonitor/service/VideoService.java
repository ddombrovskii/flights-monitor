package ru.ssau.flightsmonitor.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ssau.flightsmonitor.entity.Flight;
import ru.ssau.flightsmonitor.entity.Video;
import ru.ssau.flightsmonitor.exception.NoEntityException;
import ru.ssau.flightsmonitor.pojo.VideoPojo;
import ru.ssau.flightsmonitor.repository.FlightRepository;
import ru.ssau.flightsmonitor.repository.VideoRepository;

@Service
@AllArgsConstructor
public class VideoService {
    private final VideoRepository videoRepository;
    private final FlightRepository flightRepository;

    // create
    public VideoPojo createVideo(Long flightId, VideoPojo pojo) throws NoEntityException {
        Flight flight = flightRepository.findById(flightId)
                .orElseThrow(() -> new NoEntityException(flightId));

        Video video = videoRepository.save(Video.builder()
                .name(pojo.getName())
                .filePath(pojo.getFilePath())
                .flight(flight).build());

        return VideoPojo.fromEntity(video);
    }

    // read
    public VideoPojo readVideo(Long flightId) throws NoEntityException {
        Flight flight = flightRepository.findById(flightId)
                .orElseThrow(() -> new NoEntityException(flightId));

        Video video = videoRepository.findByFlightId(flightId);

        return VideoPojo.fromEntity(video);
    }

    // update
    public VideoPojo updateVideo(Long flightId, VideoPojo pojo) throws NoEntityException {
        Flight flight = flightRepository.findById(flightId)
                .orElseThrow(() -> new NoEntityException(flightId));

        Video videoToUpdate = videoRepository.findAll().get(0);

        videoToUpdate.setName(pojo.getName());
        videoToUpdate.setFilePath(pojo.getFilePath());

        Video video = videoRepository.save(videoToUpdate);

        return VideoPojo.fromEntity(video);
    }

    // delete
    public void deleteVideo(Long flightId, Long videoId) throws NoEntityException {
        Flight flight = flightRepository.findById(flightId)
                .orElseThrow(() -> new NoEntityException(flightId));

        Video videoToUpdate = videoRepository.findById(videoId)
                .orElseThrow(() -> new NoEntityException(videoId));

        videoRepository.deleteById(videoId);
    }
}
