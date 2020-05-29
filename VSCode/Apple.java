import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Apple {
    Rectangle apple;
    
    public Apple() {
        apple = new Rectangle(Main.blockSize, Main.blockSize, Color.RED);
        apple.setX((Math.random() * (1280 - Main.blockSize)));
        apple.setY((Math.random() * (720 - Main.blockSize)));
    }    
    

    public Rectangle returnApple()
    {
        return apple;
    }

    
}