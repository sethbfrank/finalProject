import java.util.ArrayList;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
//import sun.swing.MenuItemLayoutHelper.RectSize;
import javafx.util.Duration;
import java.util.Timer;
import java.util.TimerTask;

public class Main extends Application {

    public enum Direction {
        UP, DOWN, LEFT, RIGHT
    }

    public static final int blockSize = 40;
    private double newY, newX = 0;
    private boolean running = true;
    
    private Group snake;
    private Direction direction = Direction.RIGHT;
    private Timeline timeline = new Timeline();

    private Apple apple;
    private Obstacle obstacle;
    private Timer timer;
    private TimerTask task;
    

    public static void main(String[] args) {
        launch(args);
    }

    public Parent createContent(){
        Group root = new Group();
        timer = new Timer();
        snake = new Group();
        apple = new Apple();
        obstacle = new Obstacle();
        //task = new TimerTask(obstacle.changeX() && obstacle.changeY());
        //timer.scheduleAtFixedRate(task,5000, 5000);
        
        //snake = new ArrayList<>();
        //snake.add(new SnakeBody(250,250));
        
        
        KeyFrame frame = new KeyFrame(Duration.seconds(0.2), event -> {
            //the actual moving aspect of the snake
            if(direction == Direction.RIGHT) {
                newX = newX + 40;
                snake.setTranslateX(newX);
            }
            else if(direction == Direction.LEFT) {
                newX = newX - 40;
                snake.setTranslateX(newX);
            }
            else if(direction == Direction.UP) {
                newY = newY - 40;
                snake.setTranslateY(newY);
            }
            else if(direction == Direction.DOWN) {
                newY = newY + 40;
                snake.setTranslateY(newY);
            }

        });


        timeline.getKeyFrames().add(frame);
        timeline.setCycleCount(Timeline.INDEFINITE);

        root.getChildren().add(apple.returnApple());
        //root.getChildren().add(snake);
        BodyPart snakeBody = new BodyPart(240, 240);
        BodyPart snakeBody1 = new BodyPart(200, 240);
        snake.getChildren().add(snakeBody.addBodyPart());
        snake.getChildren().add(snakeBody1.addBodyPart());
        root.getChildren().add(snake);


        return root;
    }

    public void start(Stage primaryStage) {
        Scene scene = new Scene(createContent(),1280, 720, Color.BLACK);
        primaryStage.setScene(scene);
        
        //determines snake direction based on key input
        scene.setOnKeyPressed(event ->{
            if(event.getCode() == KeyCode.RIGHT){
                direction = Direction.RIGHT;
            }
            else if(event.getCode() == KeyCode.LEFT){
                direction = Direction.LEFT;
            }
            else if(event.getCode() == KeyCode.UP){
                direction = Direction.UP;
            }
            else if(event.getCode() == KeyCode.DOWN){
                direction = Direction.DOWN;
            }            

        });

        primaryStage.show();
        timeline.play();       
        
    }
}