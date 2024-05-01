package ru.ssau.flightsmonitor.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ssau.flightsmonitor.entity.Flight;
import ru.ssau.flightsmonitor.entity.Violation;
import ru.ssau.flightsmonitor.exception.NoEntityException;
import ru.ssau.flightsmonitor.pojo.ViolationPojo;
import ru.ssau.flightsmonitor.repository.FlightRepository;
import ru.ssau.flightsmonitor.repository.ViolationRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ViolationService {
    private final ViolationRepository violationRepository;
    private final FlightRepository flightRepository;

    // read all
    public List<ViolationPojo> readAllViolations(Long flightId) throws NoEntityException {
        Flight flight = flightRepository.findById(flightId)
                .orElseThrow(() -> new NoEntityException(flightId));

        List<Violation> violations = violationRepository.findAll();
        List<ViolationPojo> violationsPojo = new ArrayList<>();

        for (Violation violation: violations)
            violationsPojo.add(ViolationPojo.fromEntity(violation));

        return violationsPojo;
    }

    // create
    public ViolationPojo createViolation(Long flightId, ViolationPojo pojo) throws NoEntityException {
        Flight flight = flightRepository.findById(flightId)
                .orElseThrow(() -> new NoEntityException(flightId));

        Violation violation = violationRepository.save(Violation.builder()
                .name(pojo.getName())
                .description(pojo.getDescription())
                .time(pojo.getTime())
                .flight(flight).build());

        return ViolationPojo.fromEntity(violation);
    }

    // read
    public ViolationPojo readViolation(Long flightId, Long violationId) throws NoEntityException {
        Flight flight = flightRepository.findById(flightId)
                .orElseThrow(() -> new NoEntityException(flightId));

        Violation violation = violationRepository.findById(violationId)
                .orElseThrow(() -> new NoEntityException(violationId));

        return ViolationPojo.fromEntity(violation);
    }

    // update
    public ViolationPojo updateViolation(Long flightId, Long violationId, ViolationPojo pojo) throws NoEntityException {
        Flight flight = flightRepository.findById(flightId)
                .orElseThrow(() -> new NoEntityException(flightId));

        Violation violationToUpdate = violationRepository.findById(violationId)
                .orElseThrow(() -> new NoEntityException(violationId));

        violationToUpdate.setName(pojo.getName());
        violationToUpdate.setDescription(pojo.getDescription());
        violationToUpdate.setTime(pojo.getTime());

        Violation violation = violationRepository.save(violationToUpdate);

        return ViolationPojo.fromEntity(violation);
    }

    // delete
    public void deleteViolation(Long flightId, Long violationId) throws NoEntityException {
        Flight flight = flightRepository.findById(flightId)
                .orElseThrow(() -> new NoEntityException(flightId));

        Violation violationToUpdate = violationRepository.findById(violationId)
                .orElseThrow(() -> new NoEntityException(violationId));

        violationRepository.deleteById(violationId);
    }
}
