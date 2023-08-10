package solid.study.atmmachine.service;

import solid.study.atmmachine.model.AtmMoneyBox;

import java.math.BigDecimal;
import java.util.Map;

public interface AtmService {

    int putMoney(BigDecimal atmId, String cardNum, String pin, Map<Integer, Integer> notes);

    int checkBalance(String cardNum, String pin);

    Map<Integer, Integer> getMoney(BigDecimal atmId, String cardNum, String pin, int sum);
}
