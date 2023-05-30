import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Fruit apple = new Apple(10);
        Fruit apple2 = new Apple(11);
        Fruit apple3 = new Apple(12);
        Fruit apple4 = new Apple(13);
        Fruit apple5 = new Apple(14);
        Fruit orange = new Orange(14);
        Fruit orange2 = new Orange(15);
        Fruit orange3 = new Orange(16);
        Fruit orange4 = new Orange(17);
        Fruit orange5 = new Orange(18);

        Box box = new Box(new ArrayList());
        box.addFruit(apple);
        box.addFruit(apple2);
        box.addFruit(apple3);
        box.addFruit(apple4);

        Box box2 = new Box(new ArrayList());

        box2.addFruit(orange);
        box2.addFruit(orange2);
        box2.addFruit(orange3);
        box2.addFruit(orange4);
        box2.addFruit(orange5);

        Box box3 = new Box(new ArrayList());

        box.pourOver(box3);

        System.out.println(box3.getWeight());
        System.out.println(box.getWeight());

        System.out.println(box2.getWeight());
        box2.pourOver(box);
        System.out.println(box.getWeight());

    }
}
