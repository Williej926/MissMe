
import java.util.ArrayList;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Player extends Actor{
	private boolean gameOver = false;
	private boolean isInvincible = false;
	private int InvincibleCounter = 0;

	private int timeSlowedCounter = 0;

	private int amountOfLives = 3;

	boolean firstLevel = true;
	boolean secondLevel = true;

	ArrayList<Node> delete =  new ArrayList<>();
	ArrayList<Node> add =  new ArrayList<>();

	
	
	@Override
	public void act(long now) {	
		
		GameWorld gw = (GameWorld) getWorld();
		Score s = gw.getS();

		if(s.getScore()>=10 && firstLevel) {
			s.incrementLevel();
			GameWorld.incrementNextLevel();
			Obstacles.setDX(Obstacles.returnOriginal()*1.5);
			Obstacles.setOG(Obstacles.getDX());
			PowerUp.setDX(PowerUp.getDX()*1.5);
			PowerUp.setOG(PowerUp.getDX());
			firstLevel = false;
		}
		if(s.getScore()>=35 && secondLevel && !firstLevel) {
			s.incrementLevel();
			GameWorld.incrementNextLevel();
			Obstacles.setDX(Obstacles.returnOriginal()*1.5);
			Obstacles.setOG(Obstacles.getDX());
			PowerUp.setDX(PowerUp.getDX()*1.5);
			PowerUp.setOG(PowerUp.getDX());
			secondLevel = false;
		}

		if(InvincibleCounter==0) {
			////System.out.println("Not invincible");
			isInvincible = false;
			this.setImage(new Image("williejiang.gif"));
		}

		if(timeSlowedCounter==0) {
			//System.out.println("Time back to normal");
			GameWorld.setFactor(GameWorld.getOriginalFactor());
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
					//System.out.println("Although I got hit, i am currently invincible!");        		
					delete.add(obstacle);
					String uriString = Player.class.getResource("laser.wav").toString();

					Media media = new Media(uriString);
					MediaPlayer player = new MediaPlayer(media);
					player.play();
				}
				if(obstacle.getClass() == InvinciblePowerUp.class) {
					s.setScore(s.getScore()+1);
					delete.add(obstacle);
					InvincibleCounter+=500;
				}
				if(obstacle.getClass() == TimeSlowPowerUp.class) {
					s.setScore(s.getScore()+1);
					//System.out.println("I slowed down time");
					if(timeSlowedCounter<0) {
						timeSlowedCounter = 0;
						timeSlowedCounter += 700;
						GameWorld.setFactor(GameWorld.getFactor()/2);
						Obstacles.setDX(Obstacles.getDX()/2);
						PowerUp.setDX(PowerUp.getDX()/2);
					}
					else {
						timeSlowedCounter += 700;
					}
					
					delete.add(obstacle);
				}
				if(obstacle.getClass() == DestroyObstaclesPowerUp.class) {
					//System.out.println("I kill all the bad guys h33h33");
					delete.add(obstacle);
					for(Node a: getWorld().getChildren()) {
						if(((Object) a).getClass() == Obstacles.class) {
							delete.add(a);
							s.setScore(s.getScore()+1);
						}
					}
				}
				if(obstacle.getClass() == IncreaseLivePowerUp.class) {
					s.setScore(s.getScore()+1);
					//System.out.println("+1 up!");
					delete.add(obstacle);
					amountOfLives++;
					gw.getT().setText("Amount of\nlives left: " + amountOfLives);
				}

			}
			else {
				if(obstacle.getClass() == Obstacles.class) {
					s.setScore(s.getScore()-1);
					//System.out.println("-1 Life");
					amountOfLives--;
					GameWorld.getT().setText("Amount of\nlives left: " + amountOfLives);
					delete.add(obstacle);
					String uriString = Player.class.getResource("buzz.wav").toString();
					Media media = new Media(uriString);
					MediaPlayer player = new MediaPlayer(media);
					player.play();
				}
				if(obstacle.getClass() == InvinciblePowerUp.class) {
					s.setScore(s.getScore()+1);
					//System.out.println("I am invincible");
					isInvincible = true;
					InvincibleCounter = 0;
					InvincibleCounter+=500;
					this.setImage(new Image("shieldspaceship.gif"));
					delete.add(obstacle);
				}
				if(obstacle.getClass() == TimeSlowPowerUp.class) {
					s.setScore(s.getScore()+1);
					//System.out.println("I slowed down time");
					if(timeSlowedCounter<0) {
						timeSlowedCounter = 0;
						timeSlowedCounter += 700;
						GameWorld.setFactor(GameWorld.getFactor()/2);
						Obstacles.setDX(Obstacles.getDX()/2);
						PowerUp.setDX(PowerUp.getDX()/2);
					}
					else {
						timeSlowedCounter += 700;
					}
					delete.add(obstacle);
				}
				if(obstacle.getClass() == DestroyObstaclesPowerUp.class) {
					//System.out.println("I kill all the bad guys h33h33");
					delete.add(obstacle);
					for(Node a: getWorld().getChildren()) {
						if(((Node) a).getClass() == Obstacles.class) {
							delete.add(a);
							s.setScore(s.getScore()+1);
						}
					}
				}
				if(obstacle.getClass() == IncreaseLivePowerUp.class) {
					s.setScore(s.getScore()+1);
					//System.out.println("+1 up!");
					delete.add(obstacle);
					amountOfLives++;

					gw.getT().setText("Amount of\nlives left: " + amountOfLives);
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
