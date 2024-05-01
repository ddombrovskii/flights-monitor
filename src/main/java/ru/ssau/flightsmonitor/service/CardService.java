package ru.ssau.flightsmonitor.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ssau.flightsmonitor.entity.Card;
import ru.ssau.flightsmonitor.entity.Flight;
import ru.ssau.flightsmonitor.entity.Violation;
import ru.ssau.flightsmonitor.exception.NoEntityException;
import ru.ssau.flightsmonitor.pojo.CardPojo;
import ru.ssau.flightsmonitor.repository.CardRepository;
import ru.ssau.flightsmonitor.repository.FlightRepository;
import ru.ssau.flightsmonitor.repository.ViolationRepository;

@Service
@AllArgsConstructor
public class CardService {
    private final CardRepository cardRepository;
    private final FlightRepository flightRepository;
    private final ViolationRepository violationRepository;

    // create
    public CardPojo createCard(Long flightId, Long violationId, CardPojo pojo) throws NoEntityException {
        Flight flight = flightRepository.findById(flightId)
                .orElseThrow(() -> new NoEntityException(flightId));

        Violation violation = violationRepository.findById(violationId)
                .orElseThrow(() -> new NoEntityException(violationId));

        Card card = cardRepository.save(Card.builder()
                .filePath(pojo.getFilePath())
                .violation(violation).build());

        return CardPojo.fromEntity(card);
    }

    // read
    public CardPojo readCard(Long flightId, Long violationId, Long cardId) throws NoEntityException {
        Flight flight = flightRepository.findById(flightId)
                .orElseThrow(() -> new NoEntityException(flightId));

        Violation violation = violationRepository.findById(violationId)
                .orElseThrow(() -> new NoEntityException(violationId));

        Card card = cardRepository.findById(cardId)
                .orElseThrow(() -> new NoEntityException(cardId));

        return CardPojo.fromEntity(card);
    }

    // update
    public CardPojo updateCard(Long flightId, Long violationId, Long cardId, CardPojo pojo) throws NoEntityException {
        Flight flight = flightRepository.findById(flightId)
                .orElseThrow(() -> new NoEntityException(flightId));

        Violation violation = violationRepository.findById(violationId)
                .orElseThrow(() -> new NoEntityException(violationId));

        Card cardToUpdate = cardRepository.findById(cardId)
                .orElseThrow(() -> new NoEntityException(cardId));

        cardToUpdate.setFilePath(pojo.getFilePath());
        Card card = cardRepository.save(cardToUpdate);

        return CardPojo.fromEntity(card);
    }

    // delete
    public void deleteCard(Long flightId, Long violationId, Long cardId) throws NoEntityException {
        Flight flight = flightRepository.findById(flightId)
                .orElseThrow(() -> new NoEntityException(flightId));

        Violation violation = violationRepository.findById(violationId)
                .orElseThrow(() -> new NoEntityException(violationId));

        Card card = cardRepository.findById(cardId)
                .orElseThrow(() -> new NoEntityException(cardId));

        cardRepository.deleteById(cardId);
    }
}
