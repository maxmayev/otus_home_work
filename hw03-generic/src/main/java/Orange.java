public class Orange extends Fruit {
    private double weight;

    public Orange(double weight) {
        this.weight = weight;
    }


    @Override
    public double getWeight() {
        return weight;
    }
}
