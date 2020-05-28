import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Snake {
    private static final int blockSize = 40;
    private Group snakeBody;
    private static final Rectangle rectangle = new Rectangle( blockSize, blockSize, Color.RED);
    private ObservableList<Node> snake;

    public Snake(){
        snakeBody = new Group();
        snake = snakeBody.getChildren();

        Rectangle rect1 = rectangle;
        Rectangle rect2 = rectangle;
        rect1.setX(250);
        rect1.setY(250);
        snake.add(rect1);
        snake.add(rect2);

    }

    public Group returnSnake()
    {
        return snakeBody;
    }


}