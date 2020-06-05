import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class Interactions {
    private Rectangle rectangle;
    private Circle circle;
    private boolean isRectangle;
    private double x;
    private double y;
    
    public Interactions() {
        rectangle = new Rectangle(Main.blockSize, Main.blockSize, Color.WHITE);;
        rectangle.setX((Math.random() * (1280 - Main.blockSize)));
        rectangle.setY((Math.random() * (720 - Main.blockSize)));
    }

    public Interactions(int x, int y, Color color, boolean isRectangle) {
        this.isRectangle = isRectangle;
        if(isRectangle) {
            rectangle = new Rectangle(x,y,color);
            rectangle.setX(1080);
            rectangle.setY(240);
            this.x = x;
            this.y = y;
        }else{
            circle = new Circle(x,y,Main.blockSize/2,color);
            circle.setCenterX(900);
            circle.setCenterY(260);
            this.x = x;
            this.y = y;
        }
    }

    public double changeX() {
        int x = (int)(Math.random() * (1280 - Main.blockSize));
        x = x/Main.blockSize*Main.blockSize;
        if(isRectangle)
            rectangle.setX(x);
        else
            circle.setCenterX(x + Main.blockSize/2);
        this.x = x;
        return x;
    }

    public double changeY() {
        int y = (int)(Math.random() * (720 - Main.blockSize));
        y = y/Main.blockSize*Main.blockSize;
        if(isRectangle)
            rectangle.setY(y);
        else
            circle.setCenterY(y + Main.blockSize/2);
        this.y = y;
        return y;
    }

    public Rectangle returnRectangle() {
        return rectangle;
    }

    public Circle returnCircle() {
        return circle;
    }
    public double getX() {
        return x;
    }
      
    public double getY() {
        return y;
    }
}