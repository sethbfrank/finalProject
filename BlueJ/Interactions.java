import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class Interactions {
    private Rectangle rectangle;
    private Circle circle;
    private boolean isRectangle;
    private double x;
    private double y;
    
    //default constructor; creates a rectangle
    public Interactions() {
        rectangle = new Rectangle(Main.blockSize, Main.blockSize, Color.WHITE);;
        rectangle.setX((Math.random() * (1280 - Main.blockSize)));
        rectangle.setY((Math.random() * (720 - Main.blockSize)));
    }

    //creates an interaction based on the inputs
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

    //changes the x coordinate of the interaction object
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

    //changes the y coordinate of the interaction object
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

    //returns the rectangle if the object is a rectangle
    public Rectangle returnRectangle() {
        return rectangle;
    }

    //returns the circle if the oject is a circle
    public Circle returnCircle() {
        return circle;
    }
    
    //returns the x coordinate
    public double getX() {
        return x;
    }
      
    //returns the y coordinate
    public double getY() {
        return y;
    }

    public void turnBlack(){
        if(isRectangle)
            rectangle.setFill(Color.BLACK);
        else
            circle.setFill(Color.BLACK);
        return;
    }
}