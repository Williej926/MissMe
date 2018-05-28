import java.util.ArrayList;
import java.util.Random;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class GameWorld extends World{
	private static long prev = 0;
	private static double factor = 1.53333333;
	private static final double TEST = 1.533333333;
	private static double originalFactor = 1.53333333;
	private static long diff = (long) (1e9);
	private int points = 0;
	private Player player;

	private static int gameState = 0;
	private static final int HOME_SCREEN = 0;
	private static final int GAME_SCREEN = 1;
	private static final int GAME_OVER = 2;

	private static ImageView title;
	private static Button button;
	private static ImageView gameOver;
	public static Text t;
	private static Rectangle rect; 
	public static boolean setupDone = false;

	MediaPlayer player1;

	private long prevLong;
	private boolean checker = true;

	boolean tester = true;
	
	boolean musicPlayer = true;

	private int num;

	private Score s;

	public GameWorld(int width) {
		this.setWidth(width);
		

	}

	private static int counter = 0;

	public void act(long now) {
		if(musicPlayer) {
			String uriString = Player.class.getResource("music.mp3").toString();

			Media media = new Media(uriString);
			player1 = new MediaPlayer(media);
			player1.setOnEndOfMedia(new Runnable() {
			       public void run() {
			         player1.seek(Duration.ZERO);
			       }
			   });
			player1.play();
			musicPlayer = false;
		}

		//System.out.println(counter);
		if (gameState == GAME_SCREEN) {
			if(tester) {
				setNum();
				tester = false;
			}

			//System.out.println(counter);

			ArrayList<Node> deletes = new ArrayList<Node>();
			for (Node n : getChildren()) {
				if (n instanceof Actor){
					if (((Actor) n).getX() > getWidth()) {
						//System.out.println("deleted");
						//((Actor) n).getWorld().remove(((Actor) n));
						deletes.add(n);
					} else if (((Actor) n).getX() < 0) {
						//System.out.println("deleted");
						//((Actor) n).getWorld().remove(((Actor) n));


						deletes.add(n);
					}
				}
				if (n instanceof Obstacles){
					if (((Obstacles) n).getCenterX() > getWidth()) {
						//System.out.println("deleted");
						//((Actor) n).getWorld().remove(((Actor) n));
						deletes.add(n);
					} else if (((Obstacles) n).getCenterX() < 0) {
						//System.out.println("deleted");
						//((Actor) n).getWorld().remove(((Actor) n));

						points++;
						deletes.add(n);
					}
				}
				if(n instanceof Player) {
					for(Node d : ((Player) n).getDelete()) {
						deletes.add(d);
					}
					for(Node d : ((Player) n).getAdd()) {
						this.getChildren().add(d);
					}
				}

			}

			for(Node n:deletes) {
				if(n instanceof Actor) {
					if(n != null) {
						((Actor) n).getWorld().remove(((Actor) n));
					}
				}

				if(n instanceof Obstacles) {
					remove(n);
				}

			}

			if(player.isDead()) {
				if(checker) {
					prevLong = now;
					gameOver();
					for(Node a: getChildren()) {
						if(((Object) a).getClass() == Obstacles.class) {
							deletes.add(a);
						}
					}
					checker = false;
				}
				gameState = GAME_OVER;

			}



			if (now-prev>=diff/factor && checker) {
				double randY = Math.random()*getHeight();
				boolean side = Math.random()<0.5;
				if(side) {


				}

				//do something
				prev = now;

				counter++;
				if(counter==num) {
					Random rand = new Random();
					int a = rand.nextInt(4);
					if(a==0) {
						TimeSlowPowerUp tspu = new TimeSlowPowerUp();
						tspu.setX(0);
						tspu.setY(Math.random()*(this.getHeight()/1.35));
						this.getChildren().add(tspu);

					} else if(a==1){
						InvinciblePowerUp ipu = new InvinciblePowerUp();
						ipu.setX(0);
						ipu.setY(Math.random()*(this.getHeight()/1.35));
						this.getChildren().add(ipu);
					}
					else if(a==2){
						DestroyObstaclesPowerUp dopu = new DestroyObstaclesPowerUp();
						dopu.setX(0);
						dopu.setY(Math.random()*(this.getHeight()/1.35));
						this.getChildren().add(dopu);
					}
					else if(a==3) {
						IncreaseLivePowerUp ilpu = new IncreaseLivePowerUp();
						ilpu.setX(0);
						ilpu.setY(Math.random()*(this.getHeight()/1.35));
						this.getChildren().add(ilpu);
					}

					counter = 0;
					tester = true;
				}
				else {
					Obstacles ob = new Obstacles();
					ob.setRadius(100);
					ob.setCenterX(20);
					ob.setCenterY(Math.random()*(this.getHeight()));
					//                Circle c = new Circle();
					//                c.setRadius(10);
					//                c.setCenterX(0);
					//                c.setCenterY((Math.random()*(this.getHeight()))-this.getHeight()/2);
					this.getChildren().add(ob);
				}
			}
		}

	}

	public void setNum() {
		num = (int)(Math.random()*15)+10;

	}

	public static void setFactor(double x) {
		factor = x;
	}

	public static double getFactor() {
		return factor;
	}

	public static double getOriginalFactor() {
		return originalFactor;
	}
	
	public static void incrementNextLevel() {
		factor*=2;
		originalFactor*=2;
	}
	
	public int getPoints() {
		return this.points;
	}
	public void gameStart() {
		
		Image titleImage = new Image("title.gif");
		title = new ImageView(titleImage);

		title.setY(getScene().getHeight()/4);
		title.setPreserveRatio(true);
		title.setFitHeight(200);
		title.setX(getScene().getWidth()/2 - title.boundsInParentProperty().getValue().getWidth()/2);

		Image buttonImage = new Image("button_start.png", 250, 100, false, false);
		button = new Button();
		button.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				gameSetup();
				gameState = GAME_SCREEN;
				checker = true;
			}

		});
		button.setGraphic(new ImageView(buttonImage));
		button.setLayoutX(getScene().getWidth()/2 - buttonImage.getWidth()/2);
		button.setLayoutY(getScene().getHeight()*3/4);
		getChildren().addAll(title, button);

	}
	public void gameSetup() {
		player1.stop();
		musicPlayer = true;
		Obstacles.restart();
		PowerUp.restart();
		factor = TEST;
		originalFactor = TEST;
		
		this.getChildren().remove(s);
		s = new Score();
		s.setScore(0);
		s.setX(50);
		s.setY(100);
		this.getChildren().add(s);
		
		getChildren().remove(title);
		getChildren().remove(gameOver);
		getChildren().remove(button);
		gameState = GAME_SCREEN;
		Image space = new Image("williejiang.gif");

		player = new Player();
		player.setImage(space);
		setPlayer(player);
		t = new Text("Amount of\nlives left: " + player.numLives());
		t.setFill(Color.WHITE);
		rect = new Rectangle(60, 33);
		rect.setFill(Color.BLACK);
		setOnMouseMoved(new EventHandler<MouseEvent>() {
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
		
		setOnMouseDragged(new EventHandler<MouseEvent>() {
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

		getChildren().addAll(player, rect, t);

	}
	public void gameOver() {
		player.firstLevel=true;
		player.secondLevel=true;
		Image i = new Image("gameOver.png");
		gameOver = new ImageView(i);
		gameOver.setScaleX(0.5);
		gameOver.setScaleY(0.5);

		gameOver.setX(getScene().getWidth()/2 - gameOver.boundsInParentProperty().getValue().getWidth());
		gameOver.setY(-200);
		gameState = GAME_OVER;

		getChildren().add(gameOver);
		getChildren().add(button);
		getChildren().remove(rect);
		getChildren().remove(player);
		getChildren().remove(t);


		//System.exit(0);

	}

	public static Text getT() {
		return t;
	}

	public Score getS() {
		return s;
	}

	public void setPlayer(Player p) {
		player = p;
	}

}


