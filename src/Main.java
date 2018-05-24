import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.util.Calendar;
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        BorderPane bp = new BorderPane();
        primaryStage.setTitle("Obstacle Dodger");
        primaryStage.setScene(new Scene(bp, 2000, 2000));
        bp.setBackground(new Background(new BackgroundImage(new Image("background.jpg"), null, null, null, null)));
        primaryStage.show();
        GameWorld gameWorld = new GameWorld(2000);
        bp.setCenter(gameWorld);
//        Obstacles ob = new Obstacles();
//        ob.setImage(new Image("circletrash.png"));
//        ob.setX(100);
//        InvinciblePowerUp ipu = new InvinciblePowerUp();
//        ipu.setX(100);
        Image space = new Image("williejiang.gif");

        Player player = new Player();
        player.setImage(space);

        //Text t = new Text("Amount of\nlives left:" + player.numLives());


        gameWorld.setOnMouseMoved(new EventHandler<MouseEvent>() {
                                      @Override
                                      public void handle(MouseEvent event) {
                                          player.setX(event.getX() - player.getImage().getWidth()/2);
                                          player.setY(event.getY() - player.getImage().getHeight()/2);

                                      }
                                  });

        gameWorld.getChildren().addAll(player);
        gameWorld.start();

    }


    public static void main(String[] args) {
        launch(args);
    }

}
