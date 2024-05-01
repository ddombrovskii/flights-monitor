package ru.ssau.flightsmonitor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ssau.flightsmonitor.entity.Pilot;

@Repository
public interface PilotRepository extends JpaRepository<Pilot, Long> {

}
