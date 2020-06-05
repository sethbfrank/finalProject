import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class BodyPart {

    private Rectangle bodyPart;
    private double x;
    private double y;

    public BodyPart(double x, double y) {
        bodyPart = new Rectangle(Main.blockSize, Main.blockSize, Color.GREEN);
        bodyPart.setX(x);
        bodyPart.setY(y);
        this.x = x;
        this.y = y;
    }

    public Rectangle getBodyPart() {
        return bodyPart;
    }
    
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double changeX(double x) {
        bodyPart.setX(x);
        this.x = x;
        return x;
    }

    public double changeY(double y) {
        bodyPart.setY(y);
        this.y = y;
        return y;
    }
}