package solid.study.bank.service.impl;

import org.springframework.stereotype.Service;
import solid.study.bank.dao.AccountDAO;
import solid.study.bank.service.BankService;

import java.math.BigDecimal;

@Service
public class BankServiceImpl implements BankService {

    AccountDAO accountDAO;

    public BankServiceImpl(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    @Override
    public int getSumByAccountId(BigDecimal accountId) {
        return accountDAO.getAccountByAccountId(accountId).getAmount();
    }

    @Override
    public int addSumByAccountId(BigDecimal accountId, int sum) {
        int amountCurrent = accountDAO.getAccountByAccountId(accountId).getAmount();
        int amountNew = amountCurrent + sum;
        accountDAO.getAccountByAccountId(accountId).setAmount(amountNew);
        return amountNew;
    }

    @Override
    public int takeOutSumByAccountId(BigDecimal accountId, int sum) {
        int amountCurrent = accountDAO.getAccountByAccountId(accountId).getAmount();
        int amountNew = amountCurrent - sum;
        if (amountNew < 0) {
            throw new IllegalArgumentException("Not enough money");
        }
        accountDAO.getAccountByAccountId(accountId).setAmount(amountNew);
        return amountNew;
    }

    @Override
    public BigDecimal getAccountIdByCardNum(String cardNum) {
        return accountDAO.getAccountIdByCardnum(cardNum);
    }
}
