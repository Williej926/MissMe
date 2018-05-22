import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

public class Player extends Actor{

	private boolean isInvincible = false;
	private int InvincibleCounter = 0;

	private int amountOfLives = 3;
	boolean changer = true;

	ArrayList<Actor> delete =  new ArrayList<>();
	@Override
	public void act(long now) {
		if(InvincibleCounter==0) {
			System.out.println("Not invincible");
			isInvincible = false;
			this.setImage(new Image("space-ship.gif"));
		}

		InvincibleCounter--;

		delete = new ArrayList<>();
		Actor obstacle  = this.getOneIntersectingObject(Actor.class);
		if(obstacle != null) {
			if(isInvincible) {
				if(obstacle.getClass() == Obstacles.class) {
					System.out.println("Although I got hit, i am currently invincible!");        		
					delete.add(obstacle);
				}
				if(obstacle.getClass() == InvinciblePowerUp.class) {
					delete.add(obstacle);
					InvincibleCounter+=500;
				}

			}
			else {
				if(obstacle.getClass() == Obstacles.class) {
					System.out.println("-1 Life");
					amountOfLives--;
					delete.add(obstacle);
				}
				if(obstacle.getClass() == InvinciblePowerUp.class) {
					System.out.println("I am invincible");
					isInvincible = true;
					InvincibleCounter = 0;
					InvincibleCounter+=500;
					this.setImage(new Image("shield-ship.gif"));
					delete.add(obstacle);
				}
			}

		}
	}

	public ArrayList<Actor> getDelete() {
		return delete;

	}

	public boolean isDead() {
		if(amountOfLives==0) {
			return true;
		}
		return false;
	}
}
