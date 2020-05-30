import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class BodyPart {
    //private Group snakeBody;
    private Rectangle bodyPart;
    //private Rectangle bodyPart;

    public BodyPart(int x, int y){
        bodyPart = new Rectangle(Main.blockSize, Main.blockSize, Color.GREEN);
        bodyPart.setX(x);
        bodyPart.setY(y);
    }

    public Rectangle addBodyPart()
    {
        return bodyPart;
    }


}