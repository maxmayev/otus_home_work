package solid.study.card.service.impl;

import org.springframework.stereotype.Service;
import solid.study.bank.service.BankService;
import solid.study.card.dao.CardDao;
import solid.study.card.service.CardService;

import java.math.BigDecimal;

@Service
public class CardServiceImpl implements CardService {

    private final CardDao cardDao;
    private final BankService bankService;

    public CardServiceImpl(CardDao cardDao, BankService bankService) {
        this.cardDao = cardDao;
        this.bankService = bankService;
    }

    @Override
    public int getAmount(String cardNum, String pin) {
        return bankService.getSumByAccountId(checkPinAndGetAccountId(cardNum,pin));
    }

    @Override
    public int takeOutMoney(String cardNum, String pin, int sum) {
        BigDecimal accountId = checkPinAndGetAccountId(cardNum, pin);
        return bankService.takeOutSumByAccountId(accountId, sum);
    }

    @Override
    public int addMoney(String cardNum, String pin, int sum) {
        BigDecimal accountId = checkPinAndGetAccountId(cardNum, pin);
        return bankService.addSumByAccountId(accountId,sum);
    }

    @Override
    public boolean checkPin(String cardNum, String pin) {
        return cardDao.getPinByCardNum(cardNum).equals(pin);
    }

    @Override
    public boolean changePin(String cardNum, String oldPin, String newPin) {
        try {
            cardDao.changePin(cardNum, oldPin, newPin);
        } catch (Exception exception) {
            return false;
        }
        return true;
    }

    @Override
    public boolean closeCard(String cardNum, String pin) {
        try {
            cardDao.closeCard(cardNum, pin);
        } catch (Exception exception) {
            return false;
        }
        return true;
    }

    private BigDecimal checkPinAndGetAccountId(String cardNum, String pin) {
        if (!checkPin(cardNum,pin)) throw new IllegalStateException("Incorrect pin");
        return bankService.getAccountIdByCardNum(cardNum);
    }
}
