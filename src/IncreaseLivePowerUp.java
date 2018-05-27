import javafx.scene.image.Image;

public class IncreaseLivePowerUp extends PowerUp{

	public IncreaseLivePowerUp() {
		super();
		this.setImage(new Image("IncreaseLivePowerUp.png"));
		this.setScaleX(0.3);
		this.setScaleY(0.3);
	}
	
	public IncreaseLivePowerUp(Double angle,Double x, Double y) {
		super(angle, x, y);
		
		this.setImage(new Image("IncreaseLivePowerUp.png"));
		this.setScaleX(0.3);
		this.setScaleY(0.3);
		
	}

}
