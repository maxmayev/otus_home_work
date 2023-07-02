package solid.study.atmmachine.dao;

import solid.study.atmmachine.model.AtmMoneyBox;
import solid.study.atmmachine.repo.AtmRepository;

import java.math.BigDecimal;
import java.util.Map;

public class AtmDAO {
    AtmRepository atmRepository;

    public AtmDAO(AtmRepository atmRepository) {
        this.atmRepository = atmRepository;
    }

    public void putMoney(BigDecimal atmId, Map<Integer, Integer> notes) {
        AtmMoneyBox atmMoneyBox = atmRepository.getAtmMoneyBoxList().get(atmId.intValue());

        atmMoneyBox.setNote100(atmMoneyBox.getNote100() + (notes.get(100) != null ? notes.get(100) : 0));
        atmMoneyBox.setNote500(atmMoneyBox.getNote500() + (notes.get(500) != null ? notes.get(500) : 0));
        atmMoneyBox.setNote1000(atmMoneyBox.getNote1000() + (notes.get(1000) != null ? notes.get(1000) : 0));
        atmMoneyBox.setNote5000(atmMoneyBox.getNote5000() + (notes.get(5000) != null ? notes.get(5000) : 0));
    }

    public void takeOutMoney(BigDecimal atmId, Map<Integer, Integer> notes) {
        AtmMoneyBox atmMoneyBox = atmRepository.getAtmMoneyBoxList().get(atmId.intValue());
        String notEnoughNotes = "Not enough notes in MoneyBox";

        int note100 = atmMoneyBox.getNote100() - notes.get(100);
        if (note100 >= 0) atmMoneyBox.setNote100(note100);
        else throw new IllegalStateException(notEnoughNotes);

        int note500 = atmMoneyBox.getNote500() - notes.get(500);
        if (note500 >= 0)
            atmMoneyBox.setNote500(note500);
        else throw new IllegalStateException(notEnoughNotes);

        int note1000 = atmMoneyBox.getNote1000() - notes.get(1000);
        if (note1000 >= 0)
            atmMoneyBox.setNote1000(note1000);
        else throw new IllegalStateException(notEnoughNotes);

        int note5000 = atmMoneyBox.getNote5000() - notes.get(5000);
        if (note5000 >= 0)
            atmMoneyBox.setNote5000(note5000);
        else throw new IllegalStateException(notEnoughNotes);

    }

}
