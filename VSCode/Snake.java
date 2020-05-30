import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Snake {
    //private Group snakeBody;
    //private Rectangle bodyPart;
    private int xCoor;
    private int yCoor;
    private Group snake;
    //private Rectangle bodyPart;

    public Snake(int xCoor, int yCoor){
        snake = new Group();
        //snake.getChildren().add(new BodyPart(250, 250));
        //bodyPart = new Rectangle(Main.blockSize, Main.blockSize, Color.GREEN);
        this.xCoor = xCoor;
        this.yCoor = yCoor;
        //bodyPart.setX(xCoor);
        //bodyPart.setY(yCoor);
    }

    public void moveUp(){

    }
    
    
    public Rectangle addBodyPart(BodyPart bodyPart)
    {
        //snake.getChildren().add(bodyPart);
        return null;
    }


}