package solid.study.bank.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class Account {
    private final BigDecimal accountId;
    private String cardNum;
    private String currency;
    private int amount;
}
