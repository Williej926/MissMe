import javafx.scene.image.Image;

public class Heart extends Actor{

	public Heart() {
		
		this.setImage(new Image("filledHeart.png"));
		
	}
	
	@Override
	public void act(long now) {
		if(Player.gotHit()) {
			this.setImage(new Image("unfilledHeart.png"));
		}
	}

	
}
