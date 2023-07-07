package pattern.study.decorator;

public class StandardOrder implements Order {
    @Override
    public String makeOrder() {
        return "Standard order";
    }
}
