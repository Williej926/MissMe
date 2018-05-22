import javafx.scene.image.Image;

public class TimeSlowPowerUp extends PowerUp{

	public TimeSlowPowerUp() {
		super();
		this.setImage(new Image("TimeSlowPowerUpImage.png"));
		this.setScaleX(0.3);
		this.setScaleY(0.3);
	}
	
	public TimeSlowPowerUp(Double angle,Double x, Double y) {
		super(angle, x, y);
		
		this.setImage(new Image("TimeSlowPowerUpImage.png"));
		this.setScaleX(0.3);
		this.setScaleY(0.3);
		
	}

}
