package ru.ssau.flightsmonitor.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ssau.flightsmonitor.entity.Flight;
import ru.ssau.flightsmonitor.entity.Pilot;
import ru.ssau.flightsmonitor.exception.NoEntityException;
import ru.ssau.flightsmonitor.pojo.FlightPojo;
import ru.ssau.flightsmonitor.pojo.PilotPojo;
import ru.ssau.flightsmonitor.repository.FlightRepository;
import ru.ssau.flightsmonitor.repository.PilotRepository;

import java.util.*;

@Service
@AllArgsConstructor
public class FlightService {
    private final FlightRepository flightRepository;
    private final PilotRepository pilotRepository;

    // read All
    public List<FlightPojo> readAllFlights() {
        List<Flight> flights = flightRepository.findAll();
        List<FlightPojo> flightsPojo = new ArrayList<>();

        for (Flight flight : flights)
            flightsPojo.add(FlightPojo.fromEntity(flight));

        return flightsPojo;
    }

    // create
    public FlightPojo createFlight(FlightPojo pojo) {
        Flight flight = flightRepository.save(Flight.builder()
                .name(pojo.getName())
                .description(pojo.getDescription())
                .date(pojo.getDate())
                .place(pojo.getPlace()).build());

        return FlightPojo.fromEntity(flight);
    }

    // read
    public FlightPojo readFlight(Long flightId) throws NoEntityException {
        Flight flight = flightRepository.findById(flightId)
                .orElseThrow(() -> new NoEntityException(flightId));

        return FlightPojo.fromEntity(flight);
    }

    // update
    public FlightPojo updateFlight(Long flightId, FlightPojo pojo) throws NoEntityException {
        Flight flightToUpdate = flightRepository.findById(flightId)
                .orElseThrow(() -> new NoEntityException(flightId));

        flightToUpdate.setName(pojo.getName());
        flightToUpdate.setDescription(pojo.getDescription());
        flightToUpdate.setPlace(pojo.getPlace());

        Flight flight = flightRepository.save(flightToUpdate);

        return FlightPojo.fromEntity(flight);
    }

    // delete
    public void deleteFlight(Long flightId) throws NoEntityException {
        Flight flight = flightRepository.findById(flightId)
                .orElseThrow(() -> new NoEntityException(flightId));

        flightRepository.deleteById(flightId);
    }

    // add exists pilot to flight
    public void addPilotToFlight(Long flightId, Long pilotId) throws NoEntityException {
        Pilot pilot = pilotRepository.findById(pilotId)
                .orElseThrow(() -> new NoEntityException(pilotId));

        Flight flight = flightRepository.findById(flightId)
                .orElseThrow(() -> new NoEntityException(flightId));

        flight.addPilot(pilot);
        flightRepository.save(flight);
    }

    // add pilot to flight
    public PilotPojo addNewPilotToFlight(Long flightId, PilotPojo pojo) throws NoEntityException {
        Flight flight = flightRepository.findById(flightId)
                .orElseThrow(() -> new NoEntityException(flightId));

        Pilot pilot = new Pilot();
        pilot.setName(pojo.getName());
        flight.addPilot(pilot);

        return PilotPojo.fromEntity(pilotRepository.save(pilot));
    }

    // get pilots of each flight
    public HashMap<Long, List<PilotPojo>> getFlightPilots() {
        List<Flight> flights = flightRepository.findAll();

        HashMap<Long, List<PilotPojo>> flightPilots = new HashMap<>();

        for (Flight flight : flights) {
            Set<Pilot> pilots = flight.getPilots();
            List<PilotPojo> pilotsPojo = new ArrayList<>();

            for (Pilot pilot : pilots)
                pilotsPojo.add(PilotPojo.fromEntity(pilot));

            flightPilots.put(flight.getId(), pilotsPojo);
        }

        return flightPilots;
    }
}
