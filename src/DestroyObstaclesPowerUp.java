import javafx.scene.image.Image;

public class DestroyObstaclesPowerUp extends PowerUp{

	public DestroyObstaclesPowerUp() {
		super();
		this.setImage(new Image("DestroyObstaclesPowerUpImage.png"));
		this.setScaleX(0.3);
		this.setScaleY(0.3);
	}
	
	public DestroyObstaclesPowerUp(Double angle,Double x, Double y) {
		super(angle, x, y);
		
		this.setImage(new Image("DestroyObstaclesPowerUpImage.png"));
		this.setScaleX(0.3);
		this.setScaleY(0.3);
		
	}
}
