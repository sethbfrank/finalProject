import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class Obstacle {
    Rectangle obstacle;
    
    public Obstacle() {
        obstacle = new Rectangle(Main.blockSize, Main.blockSize, Color.WHITE);
        obstacle.setX((Math.random() * (1280 - Main.blockSize)));
        obstacle.setY((Math.random() * (720 - Main.blockSize)));
    }    

    public double changeX(){
        double x = (Math.random() * (1280 - Main.blockSize));
        obstacle.setX(x);
        return x;
    }
    public double changeY(){
        double y = (Math.random() * (720 - Main.blockSize));
        obstacle.setY(y);
        return y;
    }
    
    public Rectangle returnObstacle()
    {
        return obstacle;
    }
}
    