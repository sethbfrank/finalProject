import java.util.ArrayList;

import javafx.animation.KeyFrame;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
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

    public static final int blockSize = 40;
    private double newY, newX = 0;
    private boolean running = true;
    private Group snake;
    private Apple apple;
    private Obstacle obstacle;
    private Timer timer;
    private TimerTask task;


    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) {
        Group root = new Group();
        Scene scene = new Scene(root, 1280, 720, Color.BLACK);
        primaryStage.setScene(scene);
        timer = new Timer(); 

        apple = new Apple();
        //apple.setApple();
        
        obstacle = new Obstacle();
        //task = new TimerTask(obstacle.changeX() && obstacle.changeY());
        //timer.scheduleAtFixedRate(task,5000, 5000);
        
        //snake = new ArrayList<>();
        //snake.add(new SnakeBody(250,250));
        snake = new Group();
        


       
        //moving snake
        scene.setOnKeyPressed(event ->{
            if(event.getCode() == KeyCode.RIGHT){
                //change it so there's a boolean in here that will move to a separate function that's repeatadly called
                //to move indiviual parts, think about using a for loop to go through each element individually until it's end
                newX = newX + 40;
                snake.setTranslateX(newX);
            }
            else if(event.getCode() == KeyCode.LEFT){
                newX = newX - 40;
                snake.setTranslateX(newX);
            }
            else if(event.getCode() == KeyCode.UP){
                newY = newY - 40;
                snake.setTranslateY(newY);
            }
            else if(event.getCode() == KeyCode.DOWN){
                newY = newY + 40;
                snake.setTranslateY(newY);
            }            

        });

        


        //Snake snake = new Snake();
        //Group snakeGroup = snake.returnSnake();
        
        primaryStage.show();

        root.getChildren().add(apple.returnApple());
        //root.getChildren().add(snake);
        SnakeBody snakeBody = new SnakeBody(240, 240);
        SnakeBody snakeBody1 = new SnakeBody(200, 240);
        snake.getChildren().add(snakeBody.returnSnake());
        snake.getChildren().add(snakeBody1.returnSnake());
        root.getChildren().add(snake);
        




        //root.getChildren().add(snakeGroup);

    }
}