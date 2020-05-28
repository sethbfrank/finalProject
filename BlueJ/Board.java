import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Board extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) {
        Group root = new Group();
        stage.setScene(new Scene(root, 1280, 720, Color.BLACK));
        stage.show();
    }
}