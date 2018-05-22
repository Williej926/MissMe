import javafx.scene.image.Image;

public class InvinciblePowerUp extends PowerUp{

	public InvinciblePowerUp() {
		super();
		this.setImage(new Image("InvinciblePowerUpImage.png"));
		this.setScaleX(0.3);
		this.setScaleY(0.3);
	}
	
	public InvinciblePowerUp(Double angle,Double x, Double y) {
		super(angle, x, y);
		
		this.setImage(new Image("InvinciblePowerUpImage.png"));
		this.setScaleX(0.3);
		this.setScaleY(0.3);
		
	}
	
}
