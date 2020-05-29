import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class SnakeBody {
    //private Group snakeBody;
    private Rectangle bodyPart;
    private int x;
    private int y;
    //private Rectangle bodyPart;

    public SnakeBody(int x, int y){
        bodyPart = new Rectangle(Main.blockSize, Main.blockSize, Color.GREEN);
        this.x = x;
        this.y = y;
        bodyPart.setX(x);
        bodyPart.setY(y);
    }

    public Rectangle returnSnake()
    {
        return bodyPart;
    }


}