import java.util.ArrayList;

public class Snake {
        
    private ArrayList<BodyPart> snake;

    //this creates a Snake ArrayList with 3 BodyParts
    public Snake() {
        snake = new ArrayList<BodyPart>();
        BodyPart snakeBody1 = new BodyPart(0, 0);
        BodyPart snakeBody2 = new BodyPart(40, 0);    
        BodyPart snakeBody3 = new BodyPart(80, 0);
        snake.add(snakeBody3);
        snake.add(snakeBody2);
        snake.add(snakeBody1);  
    }    

    //this returns the ArrayList for snake
    public ArrayList<BodyPart> getList() {
        return snake;
    }
    
    //these methods do the same thing as methods in the ArrayList class. However, it won't need to call the getList() method before if using these methods

    public BodyPart getBodyPart(int position) {
        return snake.get(position);
    }

    //this adds a BodyPart to the snake ArrayList to a specified position
    public void addBodyPart(int position, BodyPart part) {
        snake.add(position,part);        
    }

    //removes a BodyPart from the snake ArrayList
    public BodyPart removeBodyPart(int i) {
        return snake.remove(i);
    }

    //removes all BodyParts
    public void removeAll() {
        for(int i = snake.size()-1; i > 0; i--) {
            snake.remove(i);
        }
    }

    //gets size of the snake ArrayList
    public int getSize() {
        return snake.size();
    }
}