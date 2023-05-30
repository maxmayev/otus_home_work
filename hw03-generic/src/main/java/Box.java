import java.util.ArrayList;
import java.util.List;

public class Box<T extends Fruit> implements Comparable<Box<T>> {
    private final List<T> fruits;

    public Box(ArrayList<T> fruits) {
        this.fruits = fruits;
    }

    public double getWeight() {
        return fruits.isEmpty() ? 0 : fruits.stream().mapToDouble(Fruit::getWeight).sum();
    }

    public double addFruit(T frt) {
        fruits.add(frt);
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
        if (box.fruits.equals(this.fruits)) return false;

        boolean result = box.getFruits().addAll(fruits);
        if (result) fruits.clear();
        return result;
    }

    public List<T> getFruits() {
        return fruits;
    }
}
