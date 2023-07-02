package solid.study;

import solid.study.atmmachine.dao.AtmDAO;
import solid.study.atmmachine.repo.AtmRepository;
import solid.study.atmmachine.service.AtmService;
import solid.study.atmmachine.service.impl.AtmServiceImpl;
import solid.study.bank.dao.AccountDAO;
import solid.study.bank.repo.AccountRepository;
import solid.study.bank.service.BankService;
import solid.study.bank.service.impl.BankServiceImpl;
import solid.study.card.dao.CardDao;
import solid.study.card.repo.CardRepository;
import solid.study.card.service.CardService;
import solid.study.card.service.impl.CardServiceImpl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        BankService bankService = new BankServiceImpl(new AccountDAO(new AccountRepository()));
        CardService cardService = new CardServiceImpl(new CardDao(new CardRepository()), bankService);
        AtmService atmService = new AtmServiceImpl(cardService, new AtmDAO(new AtmRepository()));

        System.out.println(atmService.checkBalance("11111","1111"));

        HashMap<Integer, Integer> notes = new HashMap<>();
        notes.put(5000, 1);
        notes.put(1000, 2);
        notes.put(500, 5);
        notes.put(100, 1);

        int newAmount = atmService.putMoney(BigDecimal.ZERO, "11111", "1111", notes);

        System.out.println("New amount " + newAmount);

        System.out.println("Check new amount " + atmService.checkBalance("11111","1111"));

        Map<Integer, Integer> money = atmService.getMoney(BigDecimal.ZERO, "11111", "1111", 6600);

        System.out.println(money);

        System.out.println("Check new amount " + atmService.checkBalance("11111","1111"));

        try {
            atmService.getMoney(BigDecimal.ZERO, "11111", "123", 100); //incorrect pin
        } catch (IllegalStateException exception) {
            System.out.println(exception.getMessage());
        }

        try {
            atmService.getMoney(BigDecimal.ZERO, "11111", "1111", 100000); // not enough money
        } catch (IllegalStateException exception) {
            System.out.println(exception.getMessage());
        }


    }

}
