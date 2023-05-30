package otus.study.cashmachine.bank.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import otus.study.cashmachine.bank.data.Account;
import otus.study.cashmachine.bank.service.impl.AccountServiceImpl;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class AccountServiceTest {
    private AccountService accountService;
    private Account testAccount;

    @BeforeEach
    private void init() {
        accountService = new AccountServiceImpl();
        testAccount = accountService.createAccount(new BigDecimal(1000));
    }

    @Test
    private void createAccount() {
        Account newAccount = accountService.createAccount(new BigDecimal(1000));
        assertEquals(0, new BigDecimal(1000).compareTo(newAccount.getAmount()));
    }

    @Test
    private void addSum() {
        BigDecimal sum = testAccount.getAmount();
        BigDecimal newSum = accountService.putMoney(testAccount.getId(), new BigDecimal(100));

        BigDecimal expectedSum = sum.add(new BigDecimal(100));
        assertEquals(0, expectedSum.compareTo(accountService.checkBalance(testAccount.getId())));
    }

    @Test
    private void getSum() {
        BigDecimal sum = testAccount.getAmount();
        BigDecimal newSum = accountService.getMoney(testAccount.getId(), new BigDecimal(100));

        BigDecimal expectedSum = sum.subtract(new BigDecimal(100));
        assertEquals(0, expectedSum.compareTo(accountService.checkBalance(testAccount.getId())));
    }

    @Test
    private void getAccount() {
        assertEquals(testAccount, accountService.getAccount(testAccount.getId()));
    }

    @Test
    private void checkBalance() {
        assertEquals(0, testAccount.getAmount()
                .compareTo(accountService.checkBalance(testAccount.getId())));
    }
}
