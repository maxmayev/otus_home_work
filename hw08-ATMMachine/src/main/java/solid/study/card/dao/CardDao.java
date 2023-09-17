package solid.study.card.dao;

import org.springframework.stereotype.Component;
import solid.study.card.model.Card;
import solid.study.card.repo.CardRepository;

import java.util.NoSuchElementException;

@Component
public class CardDao {
    CardRepository cardRepository;

    public CardDao(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public String getPinByCardNum(String cardNum) {
        Card cardByNum = cardRepository.getCards().stream().filter(card -> card.getNumber().equals(cardNum)).findFirst().orElseThrow(NoSuchElementException::new);
        return cardByNum.getPin();
    }

    public void changePin(String cardNum, String oldPin, String newPin){
        Card cardWithOldPin = cardRepository.getCards().stream().filter(card -> card.getPin().equals(oldPin)).findFirst().orElseThrow(NoSuchElementException::new);
        cardWithOldPin.setNewPin(newPin);
    }

    public void closeCard(String cardNum, String pin){
        Card crd = cardRepository.getCards().stream().filter(card -> card.getPin().equals(pin)).findFirst().orElseThrow(NoSuchElementException::new);
        cardRepository.deleteCard(crd);
    }
}
