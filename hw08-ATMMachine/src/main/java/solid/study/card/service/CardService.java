package solid.study.card.service;

public interface CardService {

    int getAmount(String cardNum, String pin);

    int takeOutMoney(String cardNum, String pin, int sum);

    int addMoney(String cardNum, String pin, int sum);

    boolean checkPin(String cardNum, String pin);

    boolean changePin(String cardNum, String oldPin, String newPin);

    boolean closeCard(String cardNum, String pin);

}
