package solid.study.atmmachine.service.impl;

import solid.study.atmmachine.dao.AtmDAO;
import solid.study.atmmachine.service.AtmService;
import solid.study.atmmachine.utils.MoneyCalculator;
import solid.study.card.service.CardService;

import java.math.BigDecimal;
import java.util.Map;

public class AtmServiceImpl implements AtmService {

    CardService cardService;
    AtmDAO atmDAO;

    public AtmServiceImpl(CardService cardService, AtmDAO atmDAO) {
        this.cardService = cardService;
        this.atmDAO = atmDAO;
    }

    @Override
    public int putMoney(BigDecimal atmId, String cardNum, String pin, Map<Integer, Integer> notes) {
        int sum = MoneyCalculator.calculateSum(notes);
        int money = cardService.addMoney(cardNum, pin, sum);
        atmDAO.putMoney(atmId, notes);
        return money;
    }

    @Override
    public int checkBalance(String cardNum, String pin) {
        return cardService.getAmount(cardNum, pin);
    }

    @Override
    public Map<Integer, Integer> getMoney(BigDecimal atmId, String cardNum, String pin, int sum) {
        if (checkBalance(cardNum,pin) >= sum) {
            Map<Integer, Integer> notes = MoneyCalculator.calculateNotes(sum);
            int sumForTakingOut = MoneyCalculator.calculateSum(notes);
            cardService.takeOutMoney(cardNum, pin, sumForTakingOut);
            atmDAO.takeOutMoney(atmId, notes);
            return notes;
        } else throw new IllegalStateException("Not enough money");
    }
}
