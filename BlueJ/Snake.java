import java.util.ArrayList;

public class Snake {
        
    private ArrayList<BodyPart> snake;

    public Snake() {
        snake = new ArrayList<BodyPart>();
        BodyPart snakeBody1 = new BodyPart(0, 0);
        BodyPart snakeBody2 = new BodyPart(40, 0);    
        BodyPart snakeBody3 = new BodyPart(80, 0);
        snake.add(snakeBody3);
        snake.add(snakeBody2);
        snake.add(snakeBody1);  
    }    

    public ArrayList<BodyPart> getList() {
        return snake;
    }
    
    public void addBodyPart(int position, BodyPart part) {
        snake.add(position,part);        
    }

    public void addBodyPart(BodyPart part) {
        snake.add(part);        
    }

    public BodyPart removeBodyPart(int i) {
        return snake.remove(i);
    }

    public int getSize() {
        return snake.size();
    }
}