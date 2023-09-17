package solid.study.bank.dao;

import org.springframework.stereotype.Component;
import solid.study.bank.model.Account;
import solid.study.bank.repo.AccountRepository;

import java.math.BigDecimal;
import java.util.NoSuchElementException;

@Component
public class AccountDAO {

    AccountRepository accountRepository;

    public AccountDAO(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account getAccountByCardNum(String cardNum) {
        return accountRepository.getAccounts().stream()
                .filter(account -> account.getCardNum().equals(cardNum))
                .findFirst().orElseThrow(NoSuchElementException::new);
    }

    public Account getAccountByAccountId(BigDecimal accountId) {
        return accountRepository.getAccounts().stream()
                .filter(account -> account.getAccountId().equals(accountId))
                .findFirst().orElseThrow(NoSuchElementException::new);
    }

    public BigDecimal getAccountIdByCardnum(String cardNum) {
        Account accountByNum = accountRepository.getAccounts().stream()
                .filter(account -> account.getCardNum().equals(cardNum))
                .findFirst().orElseThrow(NoSuchElementException::new);
        return accountByNum.getAccountId();
    }
}

