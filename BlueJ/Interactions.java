import java.util.Random;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Interactions {
    private Rectangle object;
    
    public Interactions() {
        object = new Rectangle(Main.blockSize, Main.blockSize, Color.WHITE);;
        object.setX((Math.random() * (1280 - Main.blockSize)));
        object.setY((Math.random() * (720 - Main.blockSize)));
    }

    public Interactions(int x, int y, Color color) {
        object = new Rectangle(x,y,color);
        object.setX((Math.random() * (1280 - Main.blockSize)));
        object.setY((Math.random() * (720 - Main.blockSize)));
    }

    public double changeX() {
        double x = (Math.random() * (1280 - Main.blockSize));
        object.setX(x);
        return x;
    }

    public double changeY(){
        double y = (Math.random() * (720 - Main.blockSize));
        object.setY(y);
        return y;
    }

    public Rectangle returnObject(){
        return object;
    }
}