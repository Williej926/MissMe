import java.util.ArrayList;
import java.util.Random;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GameWorld extends World{
	private static long prev = 0;
	private static double factor = 2.3;
	private static final double ORIGINAL_FACTOR = 2.3;
	private static long diff = (long) (1e9);
	private int points = 0;
	private Player player;

	private long prevLong;
	private boolean checker = true;

	boolean tester = true;
	
	private int num;
	
	private Score s;

	public GameWorld(int width) {
		this.setWidth(width);
		s = new Score();
		s.setX(50);
		s.setY(100);
		this.getChildren().add(s);
	}

	private static int counter = 0;



	public void act(long now) {

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
				checker = false;
			}


		}
		if (now-prev>=diff/factor && checker) {
			double randY = Math.random()*getHeight();
			boolean side = Math.random()<0.5;
			if(side) {


			}

			//do something
			prev = now;

			//every 10 asteroids one powerUp
			counter++;
			if(counter==num) {
				Random rand = new Random();
				int a = rand.nextInt(4);
				if(a==0) {
					TimeSlowPowerUp tspu = new TimeSlowPowerUp();
					tspu.setX(0);
					tspu.setY(Math.random()*(this.getHeight()/1.35));
					this.getChildren().add(tspu);
				}
				else if(a==1){
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
				ob.setCenterY(Math.random()*(this.getHeight()/1.35));
				//                Circle c = new Circle();
				//                c.setRadius(10);
				//                c.setCenterX(0);
				//                c.setCenterY((Math.random()*(this.getHeight()))-this.getHeight()/2);
				this.getChildren().add(ob);
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

	public static double getOrignalFactor() {
		return ORIGINAL_FACTOR;
	}
	public int getPoints() {
		return this.points;
	}

	public void gameOver() {
		Image i = new Image("gameOver.png");
		ImageView gameOver = new ImageView(i);
		gameOver.setScaleX(0.5);
		gameOver.setScaleY(0.5);

		getChildren().add(gameOver);


		//System.exit(0);

	}

	public Score getS() {
		return s;
	}

	public void setPlayer(Player p) {
		player = p;
	}

}


