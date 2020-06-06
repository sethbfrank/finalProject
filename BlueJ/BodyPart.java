import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class BodyPart {

    private Rectangle bodyPart;
    private double x;
    private double y;

    //creates a BodyPart that includes a Rectangle with its x and y coordinates
    public BodyPart(double x, double y) {
        bodyPart = new Rectangle(Main.blockSize, Main.blockSize, Color.GREEN);
        bodyPart.setX(x);
        bodyPart.setY(y);
        this.x = x;
        this.y = y;
    }

    //this returns the rectangle
    public Rectangle getBodyPart() {
        return bodyPart;
    }
    
    //this gets the x coordinate
    public double getX() {
        return x;
    }

    //this get the y coordinate
    public double getY() {
        return y;
    }

    //this changes the x coordinate
    public double changeX(double x) {
        bodyPart.setX(x);
        this.x = x;
        return x;
    }

    //this changes the y coordinate
    public double changeY(double y) {
        bodyPart.setY(y);
        this.y = y;
        return y;
    }
}