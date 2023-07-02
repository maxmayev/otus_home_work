package solid.study.card.dao;

import solid.study.card.model.Card;
import solid.study.card.repo.CardRepository;

import java.util.NoSuchElementException;

public class CardDao {
    CardRepository cardRepository;

    public CardDao(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public String getPinByCardNum(String cardNum) {
        Card cardByNum = cardRepository.getCards().stream().filter(card -> card.getNumber().equals(cardNum)).findFirst().orElseThrow(NoSuchElementException::new);
        return cardByNum.getPin();
    }
}
