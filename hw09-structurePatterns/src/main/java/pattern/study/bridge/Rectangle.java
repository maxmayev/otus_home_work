package pattern.study.bridge;

public class Rectangle extends Shape{


    public Rectangle(int sideNum, Color color) {
        super(sideNum, color);
    }

    @Override
    public String getDescription() {
        return "Rectangle: sideNum: " + sideNum + " color: " + color.getColor();
    }
}
