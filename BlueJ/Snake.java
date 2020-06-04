import java.util.ArrayList;

import javax.sound.midi.Receiver;

import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.skin.TextInputControlSkin.Direction;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Snake {
    //private Group snakeBody;
    //private Rectangle bodyPart;
    private int xCoor;
    private int yCoor;
    //private Group snake;
    //private Group snakeBody;
    private ArrayList<Rectangle> snake;
    //private Rectangle bodyPart;

    public Snake(){
        //snakeBody = new Group();
        snake = new ArrayList<>();
        BodyPart snakeBody1 = new BodyPart(0, 0);
        BodyPart snakeBody2 = new BodyPart(40, 0);    
        BodyPart snakeBody3 = new BodyPart(80, 0);
        snake.add(snakeBody3.getBodyPart());
        snake.add(snakeBody2.getBodyPart());
        snake.add(snakeBody1.getBodyPart());
        
        
        
        //snake.getChildren().add(new BodyPart(250, 250));
        //bodyPart = new Rectangle(Main.blockSize, Main.blockSize, Color.GREEN);

        //bodyPart.setX(xCoor);
        //bodyPart.setY(yCoor);
    }
    
    // public Snake(int xCoor, int yCoor){
    //     //snakeBody = new Group();
    //     snake = new Group();
    //     //snake.getChildren().add(new BodyPart(250, 250));
    //     //bodyPart = new Rectangle(Main.blockSize, Main.blockSize, Color.GREEN);
    //     this.xCoor = xCoor;
    //     this.yCoor = yCoor;
    //     //bodyPart.setX(xCoor);
    //     //bodyPart.setY(yCoor);
    // }

    public ArrayList<Rectangle> getGroup(){
        return snake;
    }
    
    
    public Node getNode(int position){
        return snake.get(position);
    }
    
    public void addBodyPart(int position, Rectangle part)
    {
        double x = part.getTranslateX();
        double y = part.getTranslateY();
        
        //double x = snake.getChildren().get(this.getSize() - 1).getTranslateX();
        //double y = snake.getChildren().get(this.getSize() - 1).getTranslateY();
        snake.add(position,part);        
    }

    public void addBodyPart(Rectangle part)
    {
        // double x = part.getTranslateX();
        // double y = part.getTranslateY();
        
        //double x = snake.getChildren().get(this.getSize() - 1).getTranslateX();
        //double y = snake.getChildren().get(this.getSize() - 1).getTranslateY();
        snake.add(part);        
    }

    public Node removeBodyPart(int i){
        return snake.remove(i);
    }

    public int getX(){
        System.out.println(xCoor);
        return xCoor;
    }

    public int getY(){
        System.out.println(yCoor);
        return yCoor;
    }

    public int getSize(){
        return snake.size();
    }


}