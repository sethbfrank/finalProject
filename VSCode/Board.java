import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Board extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) {
        Group root = new Group();
        stage.setScene(new Scene(root, 1280, 720, Color.BLACK));
        
        Apple apple = new Apple();
        apple.setApple();
        Rectangle rect = apple.returnRectangle();
        
        //Snake snake = new Snake();
        //Group snakeGroup = snake.returnSnake();
        
        stage.show();

        root.getChildren().add(rect);
        //root.getChildren().add(snakeGroup);

    }
}