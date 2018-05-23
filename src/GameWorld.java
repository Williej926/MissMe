import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.shape.Circle;

public class GameWorld extends World{
	private static long prev = 0;
	private static double factor = 2.3;
	private static final double ORIGINAL_FACTOR = 2.3;
	private static long diff = (long) (1e9);
	public GameWorld(int width) {
		this.setWidth(width);
	}

	private static int counter = 0;

	public void act(long now) {

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


					deletes.add(n);
				}
			}
			if(n instanceof Player) {
				for(Node d : ((Player) n).getDelete()) {
					deletes.add(d);
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
		if (now-prev>=diff/factor) {
			double randY = Math.random()*getHeight();
			boolean side = Math.random()<0.5;
			if(side) {


			}

			//do something
			prev = now;

			//every 10 asteroids one powerUp
			counter++;

			if(counter==10) {
				Random rand = new Random();
				int a = rand.nextInt(3);
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
				counter = 0;
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
	
	public static void setFactor(double x) {
		factor = x;
	}
	
	public static double getFactor() {
		return factor;
	}
	
	public static double getOrignalFactor() {
		return ORIGINAL_FACTOR;
	}
}
