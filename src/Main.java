import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import java.util.Calendar;
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        BorderPane bp = new BorderPane();
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(bp, 2000, 2000));
        primaryStage.show();
        GameWorld gameWorld = new GameWorld();
        bp.setCenter(gameWorld);
        Obstacles ob = new Obstacles();
        ob.setImage(new Image("AsteroidHuge.png"));
        gameWorld.getChildren().addAll(ob);
        gameWorld.start();


    }


    public static void main(String[] args) {
        launch(args);
    }
}
