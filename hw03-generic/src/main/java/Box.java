import java.util.ArrayList;

public class Box<T extends Fruit> implements Comparable<Box<T>> {
    private final ArrayList<T> fruit;

    public Box(ArrayList<T> fruit) {
        this.fruit = fruit;
    }

    public double getWeight() {
        return fruit.isEmpty() ? 0 : fruit.stream().mapToDouble(Fruit::getWeight).sum();
    }

    public double addFruit(T frt) {
        fruit.add(frt);
        return getWeight();
    }


    @Override
    public int compareTo(Box o) {
        return Double.compare(this.getWeight(), o.getWeight());
    }

    public <N extends Fruit> boolean compare(Box<N> box) {
        return this.compareTo(box) == 0;
    }

    public boolean pourOver(Box<Fruit> box) {
        boolean result = box.getFruit().addAll(fruit);
        if (result) fruit.clear();
        return result;
    }

    public ArrayList<T> getFruit() {
        return fruit;
    }
}
