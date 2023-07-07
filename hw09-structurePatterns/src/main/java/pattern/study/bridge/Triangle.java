package pattern.study.bridge;

public class Triangle extends Shape{

    public Triangle(int sideNum, Color color) {
        super(sideNum, color);
    }

    @Override
    public String getDescription() {
        return "Triangle: sideNum: " + sideNum + " color: " + color.getColor();
    }
}
