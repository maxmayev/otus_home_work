package solid.study.bank.repo;

import solid.study.bank.model.Account;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Data
public class AccountRepository {
    List<Account> accounts;

    public AccountRepository() {
        accounts = new ArrayList<>();
        accounts.add(Account.builder()
                .accountId(BigDecimal.valueOf(accounts.size()))
                .amount(1000)
                .cardNum("11111")
                .currency("Rub")
                .build());
        accounts.add(Account.builder()
                .accountId(BigDecimal.valueOf(accounts.size()))
                .amount(10000)
                .cardNum("22222")
                .currency("Rub")
                .build());
        accounts.add(Account.builder()
                .accountId(BigDecimal.valueOf(accounts.size()))
                .amount(5000)
                .cardNum("33333")
                .currency("Rub")
                .build());
    }
}
