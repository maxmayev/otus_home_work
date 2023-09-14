package solid.study.card.repo;


import org.springframework.stereotype.Repository;
import solid.study.card.model.Card;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data

@Repository
public class CardRepository {
    List<Card> cards;

    public CardRepository() {
        cards = new ArrayList<>();
        cards.add(Card.builder()
                .number("11111")
                .pin("1111")
                .userId(BigDecimal.valueOf(cards.size()))
                .build());
        cards.add(Card.builder()
                .number("22222")
                .pin("2222")
                .userId(BigDecimal.valueOf(cards.size()))
                .build());
        cards.add(Card.builder()
                .number("33333")
                .pin("3333")
                .userId(BigDecimal.valueOf(cards.size()))
                .build());
    }

    public boolean deleteCard(Card card){
        return cards.remove(card);
    }
}
