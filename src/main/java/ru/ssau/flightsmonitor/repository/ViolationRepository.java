package ru.ssau.flightsmonitor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ssau.flightsmonitor.entity.Violation;

@Repository
public interface ViolationRepository extends JpaRepository<Violation, Long> {

}
