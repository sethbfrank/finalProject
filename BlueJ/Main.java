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

public class Main extends Application {

    public static final int blockSize = 40;
    private double newY, newX = 0;
    private boolean running = true;
    private boolean up, down, left, right = false;


    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) {
        Group root = new Group();
        Scene scene = new Scene(root, 1280, 720, Color.BLACK);
        primaryStage.setScene(scene);
        
        Apple apple = new Apple();
        //apple.setApple();
        
        //moving snake
        scene.setOnKeyPressed(event ->{
            if(event.getCode() == KeyCode.RIGHT){
                newX = newX + 10;
                apple.returnApple().setTranslateX(newX);
            }
            else if(event.getCode() == KeyCode.LEFT){
                newX = newX - 10;
                apple.returnApple().setTranslateX(newX);
            }
            else if(event.getCode() == KeyCode.UP){
                newY = newY - 10;
                apple.returnApple().setTranslateY(newY);
            }
            else if(event.getCode() == KeyCode.DOWN){
                newY = newY + 10;
                apple.returnApple().setTranslateY(newY);
            }            

        });

        


        //Snake snake = new Snake();
        //Group snakeGroup = snake.returnSnake();
        
        primaryStage.show();

        root.getChildren().add(apple.returnApple());




        //root.getChildren().add(snakeGroup);

    }
}