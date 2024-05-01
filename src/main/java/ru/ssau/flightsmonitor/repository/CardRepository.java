package ru.ssau.flightsmonitor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ssau.flightsmonitor.entity.Card;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {

}
