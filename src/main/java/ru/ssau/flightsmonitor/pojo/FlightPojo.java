package ru.ssau.flightsmonitor.pojo;

import lombok.Data;
import ru.ssau.flightsmonitor.entity.Card;
import ru.ssau.flightsmonitor.entity.Flight;

import java.time.LocalDate;

@Data
public class FlightPojo {
    private Long id;
    private String name;
    private String description;
    private LocalDate date;
    private String place;

    public static FlightPojo fromEntity(Flight flight) {
        FlightPojo pojo = new FlightPojo();

        pojo.setId(flight.getId());
        pojo.setName(flight.getName());
        pojo.setDescription(flight.getDescription());
        pojo.setDate(flight.getDate());
        pojo.setPlace(flight.getPlace());

        return pojo;
    }

    public static Flight toEntity(FlightPojo pojo) {
        Flight flight = new Flight();

        flight.setId(pojo.getId());
        flight.setName(pojo.getName());
        flight.setDescription(pojo.getDescription());
        flight.setDate(pojo.getDate());
        flight.setPlace(pojo.getPlace());

        return flight;
    }
}
