package solid.study.atmmachine.repo;

import org.springframework.stereotype.Repository;
import solid.study.atmmachine.model.AtmMoneyBox;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

@Repository
public class AtmRepository {
    private List<AtmMoneyBox> atmMoneyBoxList;

    public AtmRepository() {
        atmMoneyBoxList = new LinkedList<>();

        atmMoneyBoxList.add(AtmMoneyBox.builder().
                atmId(BigDecimal.valueOf(atmMoneyBoxList.size()))
                .note100(100)
                .note500(100)
                .note1000(10)
                .note5000(5)
                .build());

        atmMoneyBoxList.add(AtmMoneyBox.builder().
                atmId(BigDecimal.valueOf(atmMoneyBoxList.size()))
                .note100(200)
                .note500(200)
                .note1000(20)
                .note5000(10)
                .build());

        atmMoneyBoxList.add(AtmMoneyBox.builder().
                atmId(BigDecimal.valueOf(atmMoneyBoxList.size()))
                .note100(300)
                .note500(300)
                .note1000(30)
                .note5000(15)
                .build());

        atmMoneyBoxList.add(AtmMoneyBox.builder().
                atmId(BigDecimal.valueOf(atmMoneyBoxList.size()))
                .note100(400)
                .note500(400)
                .note1000(40)
                .note5000(20)
                .build());

    }

    public List<AtmMoneyBox> getAtmMoneyBoxList() {
        return atmMoneyBoxList;
    }
}
