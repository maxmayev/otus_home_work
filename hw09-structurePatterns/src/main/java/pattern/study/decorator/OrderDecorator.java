package pattern.study.decorator;

public class OrderDecorator implements Order {

    Order order;

    public OrderDecorator(Order order) {
        this.order = order;
    }

    @Override
    public String makeOrder() {
        return order.makeOrder();
    }
}
