package ru.ssau.flightsmonitor.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Pilot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "pilots")
    private Set<Flight> flights = new HashSet<Flight>();

    public void addFlight(Flight flight) {
        this.flights.add(flight);
        flight.getPilots().add(this);
    }

    public void removeFlight(Flight flight) {
        this.flights.remove(flight);
        flight.getPilots().remove(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Flight)) return false;
        return id != null && id.equals(((Flight) o).getId());
    }
    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}
