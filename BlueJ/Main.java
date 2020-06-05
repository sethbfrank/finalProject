import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application {

    public enum Direction {
        UP, DOWN, LEFT, RIGHT
    }

    public static final int blockSize = 40;

    private Snake snake;
    private Direction direction = Direction.RIGHT;
    private ArrayList<BodyPart> snakeList;
    private double snakeX;
    private double snakeY;

    private Apple apple;
    private Obstacle obstacle;
    private int ticks = 0;
    
    private boolean gameOver = false;
    
    public static void main(String[] args) {
        launch(args);
    }

    //runs when the application initializes    
    public void start(Stage primaryStage) {
        //creates objects for the game
        Group root = new Group();
        snake = new Snake();
        apple = new Apple();
        obstacle = new Obstacle();

        //gets coordinates of the head of the snake
        snakeList = snake.getList();
        snakeX = snake.getList().get(0).getX();
        snakeY = snake.getList().get(0).getY();

        //sets apple and obstacle to a random point to start
        apple.changeX();
        apple.changeY();
        obstacle.changeX();
        obstacle.changeY();

        //creates the GraphicsContext which is used to display objects like snake, apple, and obstacle
        Canvas c = new Canvas(1280, 720);
        GraphicsContext gc = c.getGraphicsContext2D();
        root.getChildren().addAll(c, apple.returnCircle(), obstacle.returnRectangle());

        //this method will continuosly run our tick method
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

        //creates a new scene to be used in the application
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

        //sets up the stage and places the scene on it
        primaryStage.setScene(scene);    
        primaryStage.show();
    }

    //code that is repeated every tick
    public void tick(GraphicsContext gc) {
        
        //called when the game is over and then returns, which will leave the method
        if(gameOver){
            for(BodyPart part : snake.getList()){
                gc.setFill(Color.BLACK);
                gc.fillRect(part.getX(), part.getY(), blockSize, blockSize);
            }
            gc.setFill(Color.BLACK);
            gc.fillRect(obstacle.getX(), obstacle.getY(), blockSize, blockSize);
            gc.setFill(Color.BLACK);
            gc.fillRect(apple.getX(), apple.getY(), blockSize, blockSize);
            gc.setFill(Color.GREEN);
            gc.setFont(new Font("", 50));
            gc.fillText("GAME OVER", 500, 720/2);
            return;
        }
        
        //this variable will store the coordinates of the first block in the snake ArrayList
        double x;
        double y;

        //these are used to determine the positoin of the tail
        double lastX = snake.getList().get(snake.getSize() - 1).getX();
        double lastY = snake.getList().get(snake.getSize() - 1).getY();

        //moves all the elements in the snake up one position excpet the head since that can change directions
        for (int i = snake.getSize() - 1; i >= 1; i--) {
			snake.getList().get(i).changeX(snake.getList().get(i-1).getX());
			snake.getList().get(i).changeY(snake.getList().get(i-1).getY());
		}

        //moves the head a certain direction based on the user input
        if (direction == Direction.RIGHT) {
            x = snake.getList().get(0).getX() + blockSize;
            y = snake.getList().get(0).getY();

            snake.getList().get(0).changeX(x);
            snake.getList().get(0).changeY(y);
            
        } else if (direction == Direction.LEFT) {
            x = snake.getList().get(0).getX() - blockSize;
            y = snake.getList().get(0).getY();

            snake.getList().get(0).changeX(x);
            snake.getList().get(0).changeY(y);
            
        } else if (direction == Direction.UP) {
            x = snake.getList().get(0).getX();
            y = snake.getList().get(0).getY() - blockSize;

            snake.getList().get(0).changeX(x);
            snake.getList().get(0).changeY(y);
            
        } else if (direction == Direction.DOWN) {
            x = snake.getList().get(0).getX();
            y = snake.getList().get(0).getY() + blockSize;

            snake.getList().get(0).changeX(x);
            snake.getList().get(0).changeY(y);

        }

        //sets the new position of the snake green
        for (BodyPart part : snake.getList()) {
            gc.setFill(Color.GREEN);
            gc.fillRect(part.getX(), part.getY(), blockSize, blockSize);
        }

        //sets the old tail to black
        gc.setFill(Color.BLACK);
        gc.fillRect(lastX, lastY, blockSize, blockSize);

        //determines if the head touches an apple. If so, it adds a BodyPart to the end of the snake and changes the apple coordinates      
        if(Math.abs(snake.getList().get(0).getX() - apple.getX()) <= .1){
            if(Math.abs(snake.getList().get(0).getY() - apple.getY()) <= .1){
                snake.addBodyPart(snake.getSize() - 2, new BodyPart(lastX, lastY)); 
                apple.changeX();
                apple.changeY();
            }        
        }

        //determines if the head touches an obstacle. If so, activates the gameOver if statement
        if(Math.abs(snake.getList().get(0).getX() - obstacle.getX()) <= .1){
            if(Math.abs(snake.getList().get(0).getY() - obstacle.getY()) <= .1){
                gameOver = true;
            }            
        }
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