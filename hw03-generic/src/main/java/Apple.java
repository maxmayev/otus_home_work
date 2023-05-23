public class Apple extends Fruit{

    private double weight;

    public Apple(double weight) {
        this.weight = weight;
    }

    @Override
    public double getWeight() {
        return weight;
    }

}
