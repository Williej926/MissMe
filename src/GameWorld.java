import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.shape.Circle;
import jdk.nashorn.internal.ir.debug.ASTWriter;

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
			if(n instanceof Player) {
				for(Actor d : ((Player) n).getDelete()) {
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
				if(rand.nextInt(100)%2==0) {
					TimeSlowPowerUp tspu = new TimeSlowPowerUp();
					tspu.setX(0);
					tspu.setY(Math.random()*(this.getHeight()/1.35));
					this.getChildren().add(tspu);
				}
				else {
					InvinciblePowerUp ipu = new InvinciblePowerUp();
					ipu.setX(0);
					ipu.setY(Math.random()*(this.getHeight()/1.35));
					this.getChildren().add(ipu);
				}
				counter = 0;
			}
			else {
				Obstacles ob = new Obstacles();
				ob.setImage(new Image("circletrash.png"));
				ob.setX(0);
				ob.setY(Math.random()*(this.getHeight()/1.35));
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
