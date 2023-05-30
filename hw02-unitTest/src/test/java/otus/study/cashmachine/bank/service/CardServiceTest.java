package otus.study.cashmachine.bank.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import otus.study.cashmachine.bank.dao.CardsDao;
import otus.study.cashmachine.bank.data.Card;
import otus.study.cashmachine.bank.service.impl.CardServiceImpl;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CardServiceTest {

    @Mock
    private AccountService accountService;

    @Mock
    private CardsDao cardsDao;

    private CardService cardService;

    @BeforeEach
    private void init() {
        cardService = new CardServiceImpl(accountService, cardsDao);
    }

    @Test
    private void testCreateCard() {
        when(cardsDao.createCard("5555", 1L, "0123")).thenReturn(
                new Card(1L, "5555", 1L, "0123"));

        Card newCard = cardService.createCard("5555", 1L, "0123");


        assertNotEquals(0, newCard.getId());
        assertEquals("5555", newCard.getNumber());
        assertEquals(1L, newCard.getAccountId());
        assertEquals("0123", newCard.getPinCode());
    }

    @Test
    private void checkBalance() {
        Card card = new Card(1L, "1234", 1L, "0000");
        when(cardsDao.getCardByNumber(anyString())).thenReturn(card);
        when(accountService.checkBalance(1L)).thenReturn(new BigDecimal(1000));

        BigDecimal sum = cardService.getBalance("1234", "0000");
        assertEquals(0, sum.compareTo(new BigDecimal(1000)));
    }

    @Test
    private void getMoney() {
        ArgumentCaptor<BigDecimal> amountCaptor = ArgumentCaptor.forClass(BigDecimal.class);
        ArgumentCaptor<Long> idCaptor = ArgumentCaptor.forClass(Long.class);

        when(cardsDao.getCardByNumber("1111"))
                .thenReturn(new Card(1L, "1111", 100L, "0000"));

        when(accountService.getMoney(idCaptor.capture(), amountCaptor.capture()))
                .thenReturn(BigDecimal.TEN);

        cardService.getMoney("1111", "0000", BigDecimal.ONE);

        verify(accountService, only()).getMoney(anyLong(), any());
        assertEquals(BigDecimal.ONE, amountCaptor.getValue());
        assertEquals(100L, idCaptor.getValue().longValue());
    }

    @Test
    private void putMoney() {
        assertThrows(IllegalArgumentException.class, () -> {
            cardService.putMoney("1", "0000", BigDecimal.ONE);
        });
        when(cardsDao.getCardByNumber("1111")).thenReturn(new Card(1L, "1111", 100L, "0000"));
        when(accountService.putMoney(any(), any())).thenReturn(BigDecimal.valueOf(100));

        assertEquals(BigDecimal.valueOf(100),
                cardService.putMoney("1111", "0000", BigDecimal.valueOf(100)));

        Exception thrown = assertThrows(IllegalArgumentException.class, () -> {
            cardService.putMoney("1111", "0001", BigDecimal.valueOf(100));
        });
        assertEquals(thrown.getMessage(), "Pincode is incorrect");
    }

    @Test
    private void checkIncorrectPin() {
        Card card = new Card(1L, "1234", 1L, "0000");
        when(cardsDao.getCardByNumber(eq("1234"))).thenReturn(card);

        Exception thrown = assertThrows(IllegalArgumentException.class, () -> {
            cardService.getBalance("1234", "0012");
        });
        assertEquals(thrown.getMessage(), "Pincode is incorrect");
    }

    @Test
    private void changePin() {
        assertThrows(IllegalArgumentException.class, () -> {
            cardService.changePin("1", "0000", "1111");
        });
        ArgumentCaptor<String> cardCaptor = ArgumentCaptor.forClass(String.class);
        when(cardsDao.getCardByNumber(cardCaptor.capture())).thenReturn(new Card(1L, "111", 1L, "0000"));
        assertTrue(cardService.changePin("00012", "0000", "1111"));
        assertFalse(cardService.changePin("00012", "0001", "1111"));
        assertEquals(cardCaptor.getValue(), "00012");
    }
}