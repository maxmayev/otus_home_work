package pattern.study;

import pattern.study.decorator.Order;
import pattern.study.decorator.OrderWithBigPortion;
import pattern.study.decorator.OrderWithSmallPortion;
import pattern.study.decorator.StandardOrder;
import pattern.study.bridge.*;

public class Task {
    public static void main(String[] args) {

        //---------decorator
        Order standardOrder = new StandardOrder();
        System.out.println(standardOrder.makeOrder());

        Order bigPortion = new OrderWithBigPortion(standardOrder);

        System.out.println(bigPortion.makeOrder());

        Order smallPortion = new OrderWithSmallPortion(standardOrder);

        System.out.println(smallPortion.makeOrder());

        //---------bridge

        Shape shape = new Circle(0,new RedColor());
        System.out.println("Shape sides: " + shape.getDescription());

        Shape shape2 = new Rectangle(4,new GreenColor());
        System.out.println("Shape sides: " + shape2.getDescription());

        Shape shape3 = new Triangle(3,new BlueColor());
        System.out.println("Shape sides: " + shape3.getDescription());


    }
}
