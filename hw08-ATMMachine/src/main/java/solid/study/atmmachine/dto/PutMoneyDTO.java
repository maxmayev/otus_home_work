package solid.study.atmmachine.dto;

import lombok.Data;

import java.util.Map;

@Data
public class PutMoneyDTO {
    String atmId;
    String cardNum;
    String pin;
    Map<Integer, Integer> notes;
}
