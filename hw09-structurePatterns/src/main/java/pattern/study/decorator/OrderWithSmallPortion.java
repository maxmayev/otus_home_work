package pattern.study.decorator;

public class OrderWithSmallPortion extends OrderDecorator{
    public OrderWithSmallPortion(Order order) {
        super(order);
    }

    @Override
    public String makeOrder() {
        return super.makeOrder() + " Meking small portion";
    }
}
