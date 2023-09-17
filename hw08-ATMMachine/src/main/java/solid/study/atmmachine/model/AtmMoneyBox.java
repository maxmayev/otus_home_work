package solid.study.atmmachine.model;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Data
@Builder
public class AtmMoneyBox {
    @NonNull
    private final BigDecimal atmId;
    private int note100;
    private int note500;
    private int note1000;
    private int note5000;
}
