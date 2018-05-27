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
        gameWorld.setPlayer(player);

        t = new Text("Amount of\nlives left: " + player.numLives());
        t.setFill(Color.WHITE);
        
        Rectangle rect = new Rectangle(60, 33);
        rect.setFill(Color.BLACK);
        
       

        gameWorld.setOnMouseMoved(new EventHandler<MouseEvent>() {
        
                                      @Override
                                      public void handle(MouseEvent event) {
                                    	  
                                          player.setX(event.getX() - player.getImage().getWidth()/2);
                                          player.setY(event.getY() - player.getImage().getHeight()/2);
                                          rect.setLayoutX(event.getX() - player.getImage().getWidth()/2.8);
                                          rect.setLayoutY(player.getY() + 1.1*player.getImage().getHeight());
                                          t.setLayoutX(event.getX() - player.getImage().getWidth()/3);
                                          t.setLayoutY(player.getY() + 1.2*player.getImage().getHeight());
                                      }
                                  });

        gameWorld.getChildren().addAll(player, rect, t);
        gameWorld.start();
    }    
    public static void main(String[] args) {
        launch(args);
    }

    public static Text getT() {
    	return t;
    }
    
    
}
