package solid.study.card.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class Card {
    private final String number;
    private final BigDecimal userId;
    private final String pin;
}
