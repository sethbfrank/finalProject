import java.util.ArrayList;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
//import sun.swing.MenuItemLayoutHelper.RectSize;
import javafx.util.Duration;

public class Main extends Application {

    public enum Direction {
        UP, DOWN, LEFT, RIGHT
    }

    public static final int blockSize = 40;
    private double newY, newX = 0;
    private boolean running = true;
    
    private Snake snake;
    private Direction direction = Direction.RIGHT;
    private Timeline timeline = new Timeline();
    //private Node tail;
    private int tailIndex;
    private boolean up = false, down = false, left = false, right = false;

    private Apple apple;
    private Obstacle obstacle;
    private int ticks = 0;
    

    public static void main(String[] args) {
        launch(args);
    }

    public Parent createContent(){
        Group root = new Group();
        snake = new Snake();
        apple = new Apple();
        obstacle = new Obstacle();
        
        tailIndex = snake.getSize();

        
        KeyFrame frame = new KeyFrame(Duration.seconds(0.2), event -> {
            ticks++;
            if(ticks%25 == 0){
                obstacle.changeX();
                obstacle.changeY();
            }

          
            // if(snake.getX() == apple.getX() - blockSize/2){
            //     snake.addBodyPart(0,tail,direction);
            // }
            // if(snake.getY() == apple.getY() - blockSize/2){
            //     snake.addBodyPart(0, tail, direction);
            // }
            // if(snake.getX() == obstacle.getX() - blockSize/2){
            //     timeline.stop();
            // }
            // if(snake.getY() == obstacle.getY() - blockSize/2){
            //     timeline.stop();
            // }
            // if(snake.getX() >= 1280 || snake.getX() <= 0){
            //     timeline.stop();
            // }
            // if(snake.getY() >= 720 || snake.getY() <= 0){
            //     timeline.stop();
            // }
            



            // tailIndex--;
            // if(tailIndex == 0)
            //     tailIndex = snake.getSize()-1;
            
            //tail = snake.getNode(tailIndex);

            boolean toRemove = snake.getSize() > 1;

            Node tail  = toRemove ? snake.removeBodyPart(snake.getSize()-1) : snake.getNode(0);
            //System.out.println(tail);
            //System.out.println(tail.getTranslateX());




            // the actual moving aspect of the snake
            
            // need to utilize methods in BodyPart class to make this work
            // BodyPart random;
            // random = snake.getChildren().get(0);

            System.out.println(tail.getTranslateX());
            System.out.println(tail.getTranslateY());


            if(direction == Direction.RIGHT) {
                right = true;
                newX = newX + 40;
                
                tail.setTranslateX(snake.getNode(0).getTranslateX()+blockSize);
                tail.setTranslateY(snake.getNode(0).getTranslateY());
                //snake.removeBodyPart(snake.getSize() - 1);
                //snake.addBodyPart(0, tail, Direction.RIGHT);
            }
            else if(direction == Direction.LEFT) {
                left = true;
                newX = newX - 40;
                tail.setTranslateX(snake.getNode(0).getTranslateX()-blockSize);
                tail.setTranslateY(snake.getNode(0).getTranslateY());
                //snake.removeBodyPart(snake.getSize() - 1);
                //snake.addBodyPart(0, tail, Direction.LEFT);
            }
            else if(direction == Direction.UP) {
                up = true;
                newY = newY - 40;
                tail.setTranslateY(snake.getNode(0).getTranslateY()-blockSize);
                tail.setTranslateX(snake.getNode(0).getTranslateX());
                //snake.removeBodyPart(snake.getSize() - 1);
                //snake.addBodyPart(0, tail, Direction.UP);
            }
            else if(direction == Direction.DOWN) {
                down = true;
                newY = newY + 40;
                tail.setTranslateY(snake.getNode(0).getTranslateY()+blockSize);
                tail.setTranslateX(snake.getNode(0).getTranslateX());
                //snake.removeBodyPart(snake.getSize() - 1);
                //snake.addBodyPart(0, tail, Direction.DOWN);
            }


            if(toRemove)
                snake.addBodyPart(0, tail);
            



            // if(direction == Direction.RIGHT) {
            //     right = true;
            //     newX = newX + 40;
            //     snake.setTranslateX(newX);
            // }
            // else if(direction == Direction.LEFT) {
            //     left = true;
            //     newX = newX - 40;
            //     snake.setTranslateX(newX);
            // }
            // else if(direction == Direction.UP) {
            //     up = true;
            //     newY = newY - 40;
            //     snake.setTranslateX(newY);
            // }
            // else if(direction == Direction.DOWN) {
            //     down = true;
            //     newY = newY + 40;
            //     snake.setTranslateY(newY);
            // }

        });


        timeline.getKeyFrames().add(frame);
        timeline.setCycleCount(Timeline.INDEFINITE);

        root.getChildren().add(apple.returnCircle());
        root.getChildren().add(obstacle.returnRectangle());
        


        // BodyPart snakeBody = new BodyPart(240, 240);
        // BodyPart snakeBody1 = new BodyPart(200, 240);
        // snake.getChildren().add(snakeBody.addBodyPart());
        // snake.getChildren().add(snakeBody1.addBodyPart());
        root.getChildren().add(snake.getGroup());


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












// import java.util.ArrayList;

// import javafx.animation.KeyFrame;
// import javafx.animation.Timeline;
// import javafx.application.Application;
// import javafx.event.ActionEvent;
// import javafx.event.EventHandler;
// import javafx.scene.Group;
// import javafx.scene.Node;
// import javafx.scene.Parent;
// import javafx.scene.Scene;
// import javafx.scene.control.Button;
// import javafx.scene.input.KeyCode;
// import javafx.scene.paint.Color;
// import javafx.scene.shape.Rectangle;
// import javafx.stage.Stage;
// //import sun.swing.MenuItemLayoutHelper.RectSize;
// import javafx.util.Duration;

// public class Main extends Application {

//     public enum Direction {
//         UP, DOWN, LEFT, RIGHT
//     }

//     public static final int blockSize = 40;
//     private double newY, newX = 0;
//     private boolean running = true;
    
//     private Group snake;
//     private Direction direction = Direction.RIGHT;
//     private Timeline timeline = new Timeline();
//     private Node tail;
//     private boolean up = false, down = false, left = false, right = false;

//     private Apple apple;
//     private Obstacle obstacle;
//     private int ticks = 0;
    

//     public static void main(String[] args) {
//         launch(args);
//     }

//     public Parent createContent(){
//         Group root = new Group();
//         snake = new Group();
//         apple = new Apple();
//         obstacle = new Obstacle();
        
        
        
//         KeyFrame frame = new KeyFrame(Duration.seconds(0.2), event -> {
//             ticks++;
//             if(ticks%25 == 0){
//                 obstacle.changeX();
//                 obstacle.changeY();
//             }
//             if(ticks == 2147483647) {
//                 ticks = 0;
//             }

//             tail = snake.getChildren().get(snake.getChildren().size() - 1);
//             //the actual moving aspect of the snake
            
//             //need to utilize methods in BodyPart class to make this work
//             // if(direction == Direction.RIGHT) {
//             //     right = true;
//             //     newX = newX + 40;
                
//             //     tail.setTranslateX(snake.getChildren().get(0).getTranslateX()+blockSize);
//             //     tail.setTranslateY(snake.getChildren().get(0).getTranslateY());
//             //     snake.getChildren().remove(snake.getChildren().size() - 1);
//             //     snake.getChildren().add(0, tail);
//             // }
//             // else if(direction == Direction.LEFT) {
//             //     left = true;
//             //     newX = newX - 40;
//             //     tail.setTranslateX(snake.getChildren().get(0).getTranslateX()-blockSize);
//             //     tail.setTranslateY(snake.getChildren().get(0).getTranslateY());
//             //     snake.getChildren().remove(snake.getChildren().size() - 1);
//             //     snake.getChildren().add(0, tail);
//             // }
//             // else if(direction == Direction.UP) {
//             //     up = true;
//             //     newY = newY - 40;
//             //     tail.setTranslateY(snake.getChildren().get(0).getTranslateY()-blockSize);
//             //     tail.setTranslateX(snake.getChildren().get(0).getTranslateX());
//             //     snake.getChildren().remove(snake.getChildren().size() - 1);
//             //     snake.getChildren().add(0, tail);
//             // }
//             // else if(direction == Direction.DOWN) {
//             //     down = true;
//             //     newY = newY + 40;
//             //     tail.setTranslateY(snake.getChildren().get(0).getTranslateY()+blockSize);
//             //     tail.setTranslateX(snake.getChildren().get(0).getTranslateX());
//             //     snake.getChildren().remove(snake.getChildren().size() - 1);
//             //     snake.getChildren().add(0, tail);
//             // }





//             if(direction == Direction.RIGHT) {
//                 right = true;
//                 newX = newX + 40;
//                 snake.setTranslateX(newX);
//             }
//             else if(direction == Direction.LEFT) {
//                 left = true;
//                 newX = newX - 40;
//                 snake.setTranslateX(newX);
//             }
//             else if(direction == Direction.UP) {
//                 up = true;
//                 newY = newY - 40;
//                 snake.setTranslateX(newY);
//             }
//             else if(direction == Direction.DOWN) {
//                 down = true;
//                 newY = newY + 40;
//                 snake.setTranslateY(newY);
//             }

//         });


//         timeline.getKeyFrames().add(frame);
//         timeline.setCycleCount(Timeline.INDEFINITE);

//         root.getChildren().add(apple.returnCircle());
//         root.getChildren().add(obstacle.returnRectangle());
        


//         BodyPart snakeBody = new BodyPart(240, 240);
//         BodyPart snakeBody1 = new BodyPart(200, 240);
//         snake.getChildren().add(snakeBody.addBodyPart());
//         snake.getChildren().add(snakeBody1.addBodyPart());
//         root.getChildren().add(snake);


//         return root;
//     }

//     public void start(Stage primaryStage) {
//         Scene scene = new Scene(createContent(),1280, 720, Color.BLACK);
//         primaryStage.setScene(scene);
        
//         //determines snake direction based on key input
//         scene.setOnKeyPressed(event ->{
//             if(event.getCode() == KeyCode.RIGHT){
//                 direction = Direction.RIGHT;
//             }
//             else if(event.getCode() == KeyCode.LEFT){
//                 direction = Direction.LEFT;
//             }
//             else if(event.getCode() == KeyCode.UP){
//                 direction = Direction.UP;
//             }
//             else if(event.getCode() == KeyCode.DOWN){
//                 direction = Direction.DOWN;
//             }            

//         });

//         primaryStage.show();
//         timeline.play();       
        
//     }
// }