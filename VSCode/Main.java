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

    private Apple apple;
    private Obstacle obstacle;
    private int score = 0;
    private long speed = 200;
    private long ticks = 0;
    
    private boolean gameOver = false;
    
    //use this method to run the application
    public static void main(String[] args) {
        launch(args);
    }
    
    //creates all the necessary content
    public Group createContent() {
        //creates objects for the game
        Group root = new Group();
        snake = new Snake();
        apple = new Apple();
        obstacle = new Obstacle();

        //sets apple and obstacle to a random point to start
        apple.changeX();
        apple.changeY();
        obstacle.changeX();
        obstacle.changeY();

        //creates the GraphicsContext which is used to display objects like snake, apple, and obstacle
        Canvas c = new Canvas(1280, 720);
        GraphicsContext gc = c.getGraphicsContext2D();
        root.getChildren().addAll(c, apple.returnCircle(), obstacle.returnRectangle());

        //this method will continuosly run our tick method. It will start to speed up everytime with the speed variable
        new AnimationTimer() {
            public void handle(long arg0) {
                //delay
                try {
                    Thread.sleep(speed);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                tick(gc);
            }
        }.start();;

        return root;
    }

    //runs when the application initializes
    public void start(Stage primaryStage) {

        //creates a new scene to be used in the application
        Scene scene = new Scene(createContent(), 1280, 720, Color.BLACK);
        
        //determines snake direction based on key input
        scene.setOnKeyPressed(event ->{
            if(event.getCode() == KeyCode.RIGHT||event.getCode() == KeyCode.D){
                direction = Direction.RIGHT;
            }
            else if(event.getCode() == KeyCode.LEFT||event.getCode() == KeyCode.A){
                direction = Direction.LEFT;
            }
            else if(event.getCode() == KeyCode.UP||event.getCode() == KeyCode.W){
                direction = Direction.UP;
            }
            else if(event.getCode() == KeyCode.DOWN||event.getCode() == KeyCode.S){
                direction = Direction.DOWN;
            }
            else if(event.getCode() == KeyCode.ENTER){
                //was going to be used for restarting but haven't had the time yet
            }
        });

        //sets up the stage and places the scene on it
        primaryStage.setTitle("Amazing Snake Game!");
        primaryStage.setScene(scene);    
        primaryStage.show();
    }


    //code that is repeated every tick
    public void tick(GraphicsContext gc) {
        //changes the obstacle every 5 seconds
        ticks++;
        if(ticks%25 == 0){
            obstacle.changeX();
            obstacle.changeY();
        }
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, 150, 50);
        gc.setFill(Color.RED);
        gc.setFont(new Font("", 25));
        gc.fillText("Score: " + score, 5, 50);

        //called when the game is over and then returns, which will leave the method
        if(gameOver){
            for(BodyPart part : snake.getList()){
                gc.setFill(Color.BLACK);
                gc.fillRect(part.getX(), part.getY(), blockSize, blockSize);
            }
            apple.turnBlack();
            obstacle.turnBlack();
            gc.setFill(Color.GREEN);
            gc.setFont(new Font("", 50));
            gc.fillText("GAME OVER", 500, 720/2);
            gc.setFill(Color.RED);
            gc.setFont(new Font("", 50));
            gc.fillText("Score: " + score, 550, 450);
            return;
        }
        
        //this variable will store the coordinates of the first block in the snake ArrayList
        double x;
        double y;

        //these are used to determine the positoin of the tail
        double lastX = snake.getBodyPart(snake.getSize() - 1).getX();
        double lastY = snake.getBodyPart(snake.getSize() - 1).getY();

        //moves all the elements in the snake up one position excpet the head since that can change directions
        for (int i = snake.getSize() - 1; i >= 1; i--) {
			snake.getBodyPart(i).changeX(snake.getBodyPart(i-1).getX());
			snake.getBodyPart(i).changeY(snake.getBodyPart(i-1).getY());
		}

        //moves the head a certain direction based on the user input
        if (direction == Direction.RIGHT) {
            x = snake.getBodyPart(0).getX() + blockSize;
            y = snake.getBodyPart(0).getY();

            snake.getBodyPart(0).changeX(x);
            snake.getBodyPart(0).changeY(y);
            
        } else if (direction == Direction.LEFT) {
            x = snake.getBodyPart(0).getX() - blockSize;
            y = snake.getBodyPart(0).getY();

            snake.getBodyPart(0).changeX(x);
            snake.getBodyPart(0).changeY(y);
            
        } else if (direction == Direction.UP) {
            x = snake.getBodyPart(0).getX();
            y = snake.getBodyPart(0).getY() - blockSize;

            snake.getBodyPart(0).changeX(x);
            snake.getBodyPart(0).changeY(y);
            
        } else if (direction == Direction.DOWN) {
            x = snake.getBodyPart(0).getX();
            y = snake.getBodyPart(0).getY() + blockSize;

            snake.getBodyPart(0).changeX(x);
            snake.getBodyPart(0).changeY(y);

        }

        //sets the new position of the snake green
        for (BodyPart part : snake.getList()) {
            gc.setFill(Color.GREEN);
            gc.fillRect(part.getX(), part.getY(), blockSize, blockSize);
        }

        //sets the old tail to black
        gc.setFill(Color.BLACK);
        gc.fillRect(lastX, lastY, blockSize, blockSize);

        //determines if the head touches an apple. If so, it adds a BodyPart to the end of the snake, changes the apple coordinates, and increases the speed
        if(Math.abs(snake.getBodyPart(0).getX() - apple.getX()) <= .1){
            if(Math.abs(snake.getBodyPart(0).getY() - apple.getY()) <= .1){
                snake.addBodyPart(snake.getSize() - 2, new BodyPart(lastX, lastY)); 
                apple.changeX();
                apple.changeY();
                score++;
                if(speed > 72)
                    speed = (long)(speed/1.1);
            }        
        }

        //determines if the head touches an obstacle. If so, activates the gameOver if statement
        if(Math.abs(snake.getBodyPart(0).getX() - obstacle.getX()) <= .1){
            if(Math.abs(snake.getBodyPart(0).getY() - obstacle.getY()) <= .1){
                gameOver = true;
            }
        }

        if(snake.getBodyPart(0).getX() > 1280 - blockSize || snake.getBodyPart(0).getX() < 0){
            gameOver = true;        
        }

        if(snake.getBodyPart(0).getY() > 720 - blockSize || snake.getBodyPart(0).getY() < 0){
            gameOver = true;
        }

        for(int i = 1; i < snake.getSize(); i++) {
            if(snake.getBodyPart(0).getX() == snake.getBodyPart(i).getX())
                if(snake.getBodyPart(0).getY() == snake.getBodyPart(i).getY())
                    gameOver = true;
        }
    }    
}


//end of Main class