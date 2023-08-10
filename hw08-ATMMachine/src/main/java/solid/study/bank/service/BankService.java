package solid.study.bank.service;

import java.math.BigDecimal;

public interface BankService {
    int getSumByAccountId(BigDecimal accountId);

    int addSumByAccountId(BigDecimal accountId, int sum);

    int takeOutSumByAccountId(BigDecimal accountId, int sum);

    BigDecimal getAccountIdByCardNum(String cardNum);
}
