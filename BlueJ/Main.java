import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
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
    // private Node tail;
    private int tailIndex;
    private boolean up = false, down = false, left = false, right = false;

    private Apple apple;
    private Obstacle obstacle;
    private int ticks = 0;

    public static void main(String[] args) {
        launch(args);
    }

    //not used currently
    public Parent createContent() {
        Group root = new Group();
        snake = new Snake();
        apple = new Apple();
        obstacle = new Obstacle();

        tailIndex = snake.getSize();
       
        Canvas c = new Canvas(1280, 720);
        GraphicsContext gc = c.getGraphicsContext2D();
        root.getChildren().add(c);

        root.getChildren().add(apple.returnCircle());
        root.getChildren().add(obstacle.returnRectangle());

        
        return root;
    }
    
    public void start(Stage primaryStage) {
        Group root = new Group();
        snake = new Snake();
        apple = new Apple();
        obstacle = new Obstacle();

        Canvas c = new Canvas(1280, 720);
        GraphicsContext gc = c.getGraphicsContext2D();
        root.getChildren().add(c);

        root.getChildren().add(apple.returnCircle());
        root.getChildren().add(obstacle.returnRectangle());

        // root.getChildren().add(snake.getGroup());

        new AnimationTimer() {
            public void handle(long arg0) {
                
                
                //delay
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                tick(gc);
            }
        }.start();;


        Scene scene = new Scene(root,1280, 720, Color.BLACK);
        

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

        primaryStage.setScene(scene);    
        primaryStage.show();
        timeline.play();   


    }

    //code that is repeated every tick
    public void tick(GraphicsContext gc) {
        // gc.setFill(Color.RED);
        // gc.setFont(new Font("", 50));
        // gc.fillText("GAME OVER", 100, 250);

        double x;
        double y;

        double lastX = snake.getGroup().get(snake.getSize() - 1).getX();
        double lastY = snake.getGroup().get(snake.getSize() - 1).getY();

        for (int i = snake.getSize() - 1; i >= 1; i--) {
			snake.getGroup().get(i).setX(snake.getGroup().get(i-1).getX());
			snake.getGroup().get(i).setY(snake.getGroup().get(i-1).getY());
		}

        if (direction == Direction.RIGHT) {
            x = snake.getGroup().get(0).getX() + blockSize;
            y = snake.getGroup().get(0).getY();

            snake.getGroup().get(0).setX(x);
            snake.getGroup().get(0).setY(y);
            
        } else if (direction == Direction.LEFT) {
            x = snake.getGroup().get(0).getX() - blockSize;
            y = snake.getGroup().get(0).getY();

            snake.getGroup().get(0).setX(x);
            snake.getGroup().get(0).setY(y);
            
        } else if (direction == Direction.UP) {
            x = snake.getGroup().get(0).getX();
            y = snake.getGroup().get(0).getY() - blockSize;

            snake.getGroup().get(0).setX(x);
            snake.getGroup().get(0).setY(y);
            
        } else if (direction == Direction.DOWN) {
            x = snake.getGroup().get(0).getX();
            y = snake.getGroup().get(0).getY() + blockSize;

            snake.getGroup().get(0).setX(x);
            snake.getGroup().get(0).setY(y);

        }

        for (Rectangle rect : snake.getGroup()) {
            gc.setFill(Color.GREEN);
            gc.fillRect(rect.getX(), rect.getY(), blockSize, blockSize);
        }

        gc.setFill(Color.BLACK);
        gc.fillRect(lastX, lastY, blockSize, blockSize);


       

        if(Math.abs(snake.getGroup().get(0).getX() - apple.getX() - blockSize/2) <= .1){
            if(Math.abs(snake.getGroup().get(0).getY() - apple.getY() - blockSize/2) <= .1){
                snake.addBodyPart(0, new Rectangle(lastX, lastY, blockSize, blockSize)); 
                System.out.println(apple.getX());
                System.out.println(snake.getGroup().get(0).getX());
                System.out.println(apple.getX());
                System.out.println(snake.getGroup().get(0).getY());
            }        
        }
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

    }
    
}











/****************************************************************************** */

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

//             //tail = snake.getChildren().get(snake.getChildren().size() - 1);
            
//             boolean toRemove = snake.getChildren().size() > 1;

//             Node tail  = toRemove ? snake.getChildren().remove(snake.getChildren().size()-1) : snake.getChildren().get(0);
            
//             double tailX = tail.getTranslateX();
//             double tailY = tail.getTranslateY();
            
//             //the actual moving aspect of the snake
            
//             //need to utilize methods in BodyPart class to make this work
//             if(direction == Direction.RIGHT) {
//                 right = true;
//                 newX = newX + 40;
                
//                 tail.setTranslateX(snake.getChildren().get(0).getTranslateX()+blockSize);
//                 tail.setTranslateY(snake.getChildren().get(0).getTranslateY());
//                 //snake.getChildren().remove(snake.getChildren().size() - 1);
//                 //snake.getChildren().add(0, tail);
//             }
//             else if(direction == Direction.LEFT) {
//                 left = true;
//                 newX = newX - 40;
//                 tail.setTranslateX(snake.getChildren().get(0).getTranslateX()-blockSize);
//                 tail.setTranslateY(snake.getChildren().get(0).getTranslateY());
//                 //snake.getChildren().remove(snake.getChildren().size() - 1);
//                 //snake.getChildren().add(0, tail);
//             }
//             else if(direction == Direction.UP) {
//                 up = true;
//                 newY = newY - 40;
//                 tail.setTranslateY(snake.getChildren().get(0).getTranslateY()-blockSize);
//                 tail.setTranslateX(snake.getChildren().get(0).getTranslateX());
//                 //snake.getChildren().remove(snake.getChildren().size() - 1);
//                 //snake.getChildren().add(0, tail);
//             }
//             else if(direction == Direction.DOWN) {
//                 down = true;
//                 newY = newY + 40;
//                 tail.setTranslateY(snake.getChildren().get(0).getTranslateY()+blockSize);
//                 tail.setTranslateX(snake.getChildren().get(0).getTranslateX());
//                 //snake.getChildren().remove(snake.getChildren().size() - 1);
//                 //snake.getChildren().add(0, tail);
//             }

//             if(toRemove)
//                 snake.getChildren().add(0, tail);

//             if(ticks%25 == 0){
//                 Rectangle rect = new Rectangle(blockSize, blockSize);
//                 rect.setTranslateX(tailX);
//                 rect.setTranslateY(tailY);
//                 snake.getChildren().add(rect);
//             }


//             // if(direction == Direction.RIGHT) {
//             //     right = true;
//             //     newX = newX + 40;
//             //     snake.setTranslateX(newX);
//             // }
//             // else if(direction == Direction.LEFT) {
//             //     left = true;
//             //     newX = newX - 40;
//             //     snake.setTranslateX(newX);
//             // }
//             // else if(direction == Direction.UP) {
//             //     up = true;
//             //     newY = newY - 40;
//             //     snake.setTranslateX(newY);
//             // }
//             // else if(direction == Direction.DOWN) {
//             //     down = true;
//             //     newY = newY + 40;
//             //     snake.setTranslateY(newY);
//             // }

//         });


//         timeline.getKeyFrames().add(frame);
//         timeline.setCycleCount(Timeline.INDEFINITE);

//         root.getChildren().add(apple.returnCircle());
//         root.getChildren().add(obstacle.returnRectangle());
        


//         BodyPart snakeBody = new BodyPart(240, 240);
//         BodyPart snakeBody1 = new BodyPart(200, 240);
//         snake.getChildren().add(snakeBody.getBodyPart());
//         snake.getChildren().add(snakeBody1.getBodyPart());
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