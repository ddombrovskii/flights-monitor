package ru.ssau.flightsmonitor.pojo;

import lombok.Data;
import ru.ssau.flightsmonitor.entity.Card;

@Data
public class CardPojo {
    private Long id;
    private String filePath;

    public static CardPojo fromEntity(Card card) {
        CardPojo pojo = new CardPojo();

        pojo.setId(card.getId());
        pojo.setFilePath(card.getFilePath());

        return pojo;
    }

    public static Card toEntity(CardPojo pojo) {
        Card card = new Card();

        card.setId(pojo.getId());
        card.setFilePath(pojo.getFilePath());

        return card;
    }
}
