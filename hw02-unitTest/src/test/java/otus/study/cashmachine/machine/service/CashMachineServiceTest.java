package otus.study.cashmachine.machine.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;
import otus.study.cashmachine.TestUtil;
import otus.study.cashmachine.bank.dao.CardsDao;
import otus.study.cashmachine.bank.data.Card;
import otus.study.cashmachine.bank.service.AccountService;
import otus.study.cashmachine.bank.service.impl.CardServiceImpl;
import otus.study.cashmachine.machine.data.CashMachine;
import otus.study.cashmachine.machine.data.MoneyBox;
import otus.study.cashmachine.machine.service.impl.CashMachineServiceImpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CashMachineServiceTest {

    @Spy
    @InjectMocks
    private CardServiceImpl cardService;

    @Mock
    private CardsDao cardsDao;

    @Mock
    private AccountService accountService;

    @Mock
    private MoneyBoxService moneyBoxService;

    private CashMachineServiceImpl cashMachineService;

    private CashMachine cashMachine = new CashMachine(new MoneyBox());

    @BeforeEach
    void init() {
        cashMachineService = new CashMachineServiceImpl(cardService, accountService, moneyBoxService);
    }


    @Test
    void getMoney() {
        doReturn(BigDecimal.ONE)
                .when(cardService)
                .getMoney(eq("0000"), any(), same(BigDecimal.valueOf(10)));

        when(moneyBoxService.getMoney(anyInt()))
                .thenReturn(List.of(1, 1, 1, 1));

        CashMachine machine = new CashMachine(new MoneyBox());

        List<Integer> result = cashMachineService.getMoney(machine, "0000", "34543", BigDecimal.valueOf(10));
        assertThrows(IllegalArgumentException.class, () -> cashMachineService.getMoney(machine, "0000", "34543", BigDecimal.valueOf(11)));

        assertEquals(List.of(1, 1, 1, 1), result);

    }

    @Test
    void putMoney() {
        when(cardsDao.getCardByNumber("1111")).thenReturn(new Card(1L, "1111", 1L, "1111"));
        when(accountService.putMoney(1L, BigDecimal.ONE)).thenReturn(BigDecimal.ZERO);

        BigDecimal result = cardService.putMoney("1111", "1111", BigDecimal.ONE);
        assertEquals(BigDecimal.ZERO,result);
    }

    @Test
    void checkBalance() {

    }

    @Test
    void changePin() {
// @TODO create change pin test using spy as implementation and ArgumentCaptor and thenReturn
        // given

        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);

        when(cardsDao.getCardByNumber(captor.capture()))
                .thenReturn(new Card(1L, "1111", 1L, TestUtil.getHash("1234")));

        cardService.changePin("1111", "1234", "7890");

        assertEquals("1111", captor.getValue());

        doReturn(true).when(cardService).changePin(anyString(), anyString(), anyString());
        boolean success = cardService.changePin("1111", "1234", "123456789");

        assertTrue(success);
    }

    @Test
    void changePinWithAnswer() {
// @TODO create change pin test using spy as implementation and mock an thenAnswer


        List<String> numbers = new ArrayList<>();

        when(cardsDao.getCardByNumber(same("5432")))
                .thenAnswer(new Answer<Card>() {
                    @Override
                    public Card answer(InvocationOnMock invocation) throws Throwable {
                        numbers.add(invocation.getArgument(0));
                        return new Card(1L, "1234", 1L, TestUtil.getHash("1234"));
                    }
                });


        cardService.changePin("5432", "1234", "4321");

        assertEquals(numbers.get(0), "5432");

        doReturn(new Card(1L, "1234", 1L, TestUtil.getHash("1234"))).when(cardsDao).getCardByNumber("5432");

        assertEquals(false, cardService.changePin("5432", "1234", "4321"));


    }
}