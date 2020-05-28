import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Apple {
    public static final int blockSize = 40;
    Rectangle apple;
    
    public Apple() {
        apple = new Rectangle( blockSize, blockSize, Color.RED);
    }    
    
    public void setApple() {
        apple.setX((Math.random() * (1280 - blockSize)));
        apple.setY((Math.random() * (720 - blockSize)));
    }

    public Rectangle returnRectangle()
    {
        return apple;
    }

    
}