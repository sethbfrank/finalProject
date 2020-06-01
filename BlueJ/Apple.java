import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Apple extends Interactions{
    
    public Apple() {
        super(Main.blockSize, Main.blockSize, Color.RED);
    }    

    public double changeX(){
        return super.changeX();
    }

    public double changeY(){
        return super.changeY();
    }

    public Rectangle returnApple()
    {
        return super.returnObject();
    }

    
}