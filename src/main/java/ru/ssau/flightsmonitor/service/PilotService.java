package ru.ssau.flightsmonitor.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ssau.flightsmonitor.entity.Flight;
import ru.ssau.flightsmonitor.entity.Pilot;
import ru.ssau.flightsmonitor.exception.NoEntityException;
import ru.ssau.flightsmonitor.pojo.PilotPojo;
import ru.ssau.flightsmonitor.repository.FlightRepository;
import ru.ssau.flightsmonitor.repository.PilotRepository;

import java.util.*;

@Service
@AllArgsConstructor
public class PilotService {
    private final PilotRepository pilotRepository;
    private final FlightRepository flightRepository;

    public List<PilotPojo> readAllPilots() {
        List<Pilot> pilots = pilotRepository.findAll();
        List<PilotPojo> pilotsPojo = new ArrayList<>();

        for (Pilot pilot : pilots)
            pilotsPojo.add(PilotPojo.fromEntity(pilot));

        return pilotsPojo;
    }

    // create
    public PilotPojo createPilot(PilotPojo pojo) {
        Pilot pilot = pilotRepository.save(Pilot.builder()
                .name(pojo.getName()).build());

        return PilotPojo.fromEntity(pilot);
    }

    // read
    public PilotPojo readPilot(Long pilotId) throws NoEntityException {
        Pilot pilot = pilotRepository.findById(pilotId)
                .orElseThrow(() -> new NoEntityException(pilotId));

        return PilotPojo.fromEntity(pilot);
    }

    // update
    public PilotPojo updatePilot(Long pilotId, PilotPojo pojo) throws NoEntityException {
        Pilot pilotToUpdate = pilotRepository.findById(pilotId)
                .orElseThrow(() -> new NoEntityException(pilotId));

        pilotToUpdate.setName(pojo.getName());
        Pilot pilot = pilotRepository.save(pilotToUpdate);

        return PilotPojo.fromEntity(pilot);
    }

    // delete
    public void deletePilot(Long pilotId) throws NoEntityException {
        Pilot pilot = pilotRepository.findById(pilotId)
                .orElseThrow(() -> new NoEntityException(pilotId));

        Set<Flight> flights = pilot.getFlights();
        for (Iterator<Flight> iter = flights.iterator(); iter.hasNext(); ) {
            Flight f = iter.next();
            iter.remove();
        }

        pilotRepository.deleteById(pilotId);
    }

    // get flights count of each pilot
    public HashMap<Long, Integer> getCountOfFlights() {
        HashMap<Long, Integer> count = new HashMap<>();
        List<Pilot> pilots = pilotRepository.findAll();

        for (Pilot pilot : pilots)
            count.put(pilot.getId(), pilot.getFlights().size());

        return count;
    }
}
