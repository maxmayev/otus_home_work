package pattern.study.bridge;

public class Circle extends Shape {


    public Circle(int sideNum, Color color) {
        super(sideNum, color);
    }

    @Override
    public String getDescription() {
        return "Circle: sideNum: " + sideNum + " color: " + color.getColor();
    }
}
