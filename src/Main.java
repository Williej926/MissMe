import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
public class Main extends Application {


	private static Text t;
	
    @Override
    public void start(Stage primaryStage) throws Exception{
    	//GAME_SCREEN ELEMENTS
    	
    	
        BorderPane bp = new BorderPane();
        primaryStage.setTitle("Miss Me!");
        primaryStage.setScene(new Scene(bp, 1500, 1000));
        bp.setBackground(new Background(new BackgroundImage(new Image("background.jpg"), null, null, null, null)));
        primaryStage.show();
        GameWorld gameWorld = new GameWorld(1000);
        bp.setCenter(gameWorld);

        Rectangle rect = new Rectangle(60, 33);
        rect.setFill(Color.BLACK);
        


        gameWorld.gameStart();
        gameWorld.start();
        
        
        
    }    
    public static void main(String[] args) {
        launch(args);
    }


    public static Text getT() {
    	return t;
    }

    
}
