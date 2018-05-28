import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class Obstacles extends Circle{
    private static double moveDistance = 5;
    private static final double TEST = 5;
    private static double ORIGINAL_DX = 5;


	public Obstacles() {
        this.setRotate(0);
        this.setScaleX(0.5);
		this.setScaleY(0.5);
		this.setFill(new ImagePattern(new Image("AsteroidHuge.png")));

    }
    public Obstacles(Double angle,Double x, Double y) {
            this.setRotate(angle);
            this.setCenterX(x);
            this.setCenterY(y);
            this.setScaleX(0.5);
    		this.setScaleY(0.5);
    }
    public void act(long now) {
        //System.out.println("X: " + getX() + " Y: " + getY());
        this.setRotate(this.getRotate() + moveDistance);
        this.setCenterX(getCenterX()+getDX());

    }

    public static void setDX(double x) {
    	moveDistance = x;
    }

    public static double getDX() {
    	return  moveDistance;
    }
    
    public static void restart() {
    	moveDistance = TEST;
    	ORIGINAL_DX = TEST;
    }

    public static void setOG(double x) {
    	ORIGINAL_DX = x;
    }

    public static double returnOriginal() {
    	return ORIGINAL_DX;
    }
}
