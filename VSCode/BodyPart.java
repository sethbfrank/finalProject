import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class BodyPart {

    private Rectangle bodyPart;
    private int x;
    private int y;

    public BodyPart(int x, int y){
        bodyPart = new Rectangle(Main.blockSize, Main.blockSize, Color.GREEN);
        bodyPart.setX(x);
        bodyPart.setY(y);
        this.x = x;
        this.y = y;
    }

    public Rectangle addBodyPart()
    {
        return bodyPart;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public int changeX(int x){
        bodyPart.setTranslateX(x);
        this.x = x;
        return x;
    }

    public int changeY(int y){
        bodyPart.setTranslateX(y);
        this.y = y;
        return y;
    }


}