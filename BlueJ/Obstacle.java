import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class Obstacle extends Interactions{
    
    public Obstacle() {
        super(Main.blockSize, Main.blockSize, Color.WHITE);
    }    

    public double changeX(){
        return super.changeX();
    }

    public double changeY(){
        return super.changeY();
    }
    
    public Rectangle returnObstacle()
    {
        return super.returnObject();
    }
}
    