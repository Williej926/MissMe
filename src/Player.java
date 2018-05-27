import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.Text;

public class Player extends Actor{
	private boolean gameOver = false;
	private boolean isInvincible = false;
	private int InvincibleCounter = 0;

	private int timeSlowedCounter = 0;

	private int amountOfLives = 3;

	ArrayList<Node> delete =  new ArrayList<>();
	ArrayList<Node> add =  new ArrayList<>();

	@Override
	public void act(long now) {	
		GameWorld gw = (GameWorld) getWorld();
		Score s = gw.getS();
		if(InvincibleCounter==0) {
			System.out.println("Not invincible");
			isInvincible = false;
			this.setImage(new Image("williejiang.gif"));
		}

		if(timeSlowedCounter==0) {
			System.out.println("Time back to normal");
			GameWorld.setFactor(GameWorld.getOrignalFactor());
			Obstacles.setDX(Obstacles.returnOriginal());
			PowerUp.setDX(PowerUp.returnOriginal());
		}

		InvincibleCounter--;
		timeSlowedCounter--;

		delete = new ArrayList<>();
		add = new ArrayList<>();
		Node obstacle  = this.getOneIntersectingObject(Node.class);
		if(obstacle != null) {
			if(isInvincible) {
				if(obstacle.getClass() == Obstacles.class) {
					s.setScore(s.getScore()+1);
					System.out.println("Although I got hit, i am currently invincible!");        		
					delete.add(obstacle);
				}
				if(obstacle.getClass() == InvinciblePowerUp.class) {
					s.setScore(s.getScore()+1);
					delete.add(obstacle);
					InvincibleCounter+=500;
				}
				if(obstacle.getClass() == TimeSlowPowerUp.class) {
					s.setScore(s.getScore()+1);
					System.out.println("I slowed down time");
					if(timeSlowedCounter<0) {
						timeSlowedCounter = 0;
						timeSlowedCounter += 700;
					}
					else {
						timeSlowedCounter += 700;
					}
					GameWorld.setFactor(GameWorld.getFactor()/2);
					Obstacles.setDX(Obstacles.getDX()/2);
					PowerUp.setDX(PowerUp.getDX()/2);
					delete.add(obstacle);
				}
				if(obstacle.getClass() == DestroyObstaclesPowerUp.class) {
					System.out.println("I kill all the bad guys h33h33");
					delete.add(obstacle);
					for(Node a: getWorld().getChildren()) {
						if(((Object) a).getClass() == Obstacles.class) {
							delete.add(a);
							s.setScore(s.getScore()+1);
						}
					}
				}

			}
			else {
				if(obstacle.getClass() == Obstacles.class) {
					s.setScore(s.getScore()-1);
					System.out.println("-1 Life");
					amountOfLives--;
					Main.getT().setText("Amount of\nlives left: " + amountOfLives);
					delete.add(obstacle);
				}
				if(obstacle.getClass() == InvinciblePowerUp.class) {
					s.setScore(s.getScore()+1);
					System.out.println("I am invincible");
					isInvincible = true;
					InvincibleCounter = 0;
					InvincibleCounter+=500;
					this.setImage(new Image("shieldspaceship.gif"));
					delete.add(obstacle);
				}
				if(obstacle.getClass() == TimeSlowPowerUp.class) {
					s.setScore(s.getScore()+1);
					System.out.println("I slowed down time");
					timeSlowedCounter = 0;
					timeSlowedCounter += 700;
					GameWorld.setFactor(GameWorld.getFactor()/2);
					Obstacles.setDX(Obstacles.getDX()/2);
					PowerUp.setDX(PowerUp.getDX()/2);
					delete.add(obstacle);
				}
				if(obstacle.getClass() == DestroyObstaclesPowerUp.class) {
					System.out.println("I kill all the bad guys h33h33");
					delete.add(obstacle);
					for(Node a: getWorld().getChildren()) {
						if(((Node) a).getClass() == Obstacles.class) {
							delete.add(a);
							s.setScore(s.getScore()+1);
						}
					}
				}
			}

		}
		if(this.amountOfLives == 0) {
			//Platform.exit();
//			if(getWorld() instanceof GameWorld) {
//				((GameWorld) getWorld()).gameOver();
//			}
			gameOver = true;
		
			
		}
	}

	public ArrayList<Node> getDelete() {
		return delete;

	}
	public ArrayList<Node> getAdd(){
		return add;
	}

	public boolean isDead() {

		if(amountOfLives==0) {
			return true;
		}
		return false;
	}

	public int numLives() {
		return amountOfLives;
	}
}
