package pattern.study.decorator;

public class OrderWithBigPortion extends OrderDecorator{

    public OrderWithBigPortion(Order order) {
        super(order);
    }

    @Override
    public String makeOrder() {
        return super.makeOrder() + " Making big portion";
    }
}
