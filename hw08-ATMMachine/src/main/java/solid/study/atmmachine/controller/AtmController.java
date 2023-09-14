package solid.study.atmmachine.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import solid.study.atmmachine.dto.PutMoneyDTO;
import solid.study.atmmachine.service.AtmService;
import solid.study.card.service.CardService;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
public class AtmController {

    @Autowired
    AtmService atmService;
    @Autowired
    CardService cardService;

    @PostMapping(value = "money", consumes = {"application/json"})
    public ResponseEntity<Map<Integer, Integer>> putMoney(@RequestBody PutMoneyDTO money) {
        atmService.putMoney(BigDecimal.valueOf(Long.parseLong(money.getAtmId())), money.getCardNum(), money.getPin(), money.getNotes());
        return ResponseEntity.ok(money.getNotes());
    }

    @DeleteMapping(value = "card")
    public boolean closeCard(String cardNum, String pin) {
        return cardService.closeCard(cardNum, pin);
    }

    @GetMapping(value = "money")
    public ResponseEntity getMoney(BigDecimal atmId, String cardNum, String pin, int sum) {
        Map<Integer, Integer> money = new HashMap<>();
        try {
            money = atmService.getMoney(atmId, cardNum, pin, sum);
        } catch (IllegalStateException ex) {
            if (ex.getMessage().equals("Not enough money"))
                return new ResponseEntity<>("Not enough money", HttpStatus.FORBIDDEN);
            else
                return new ResponseEntity<>("Invalid pin", HttpStatus.FORBIDDEN);
        }
        return ResponseEntity.ok(money.entrySet().stream().mapToInt(element -> element.getKey() * element.getValue()).sum());
    }

    @GetMapping(value = "money/balance", produces = "text/plain;charset=UTF-8")
    public ResponseEntity getBalance(String cardNum, String pin) {
        int sum = 0;
        try {
            sum = atmService.checkBalance(cardNum, pin);
        } catch (NoSuchElementException exception) {
            return new ResponseEntity<>("No such card number in system", HttpStatus.NOT_FOUND);
        } catch (IllegalStateException ex) {
            return new ResponseEntity<>("Invalid pin", HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(String.valueOf(sum), HttpStatus.OK);
    }

    @PatchMapping(value = "card")
    public ResponseEntity changePin(String cardNum, String oldPin, String newPin) {
        boolean success = cardService.changePin(cardNum, oldPin, newPin);
        return ResponseEntity.ok(success);
    }


}
