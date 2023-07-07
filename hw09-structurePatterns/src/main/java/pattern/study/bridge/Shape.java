package pattern.study.bridge;

public abstract class Shape {
    protected Color color;

    protected int sideNum;

    public Shape(int sideNum,Color color) {
        this.sideNum = sideNum;
        this.color = color;
    }


    public abstract String getDescription();
}
