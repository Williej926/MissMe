
public class PowerUp extends Actor {

    private static double moveDistance = 10;
    private static final double ORIGINAL_DX = 10;

    
	public PowerUp() {
		this.setRotate(0);
	}

	public PowerUp(Double angle,Double x, Double y) {
		this.setRotate(angle);
		this.setX(x);
		this.setY(y);
	}

	@Override
	public void act(long now) {
		//System.out.println("X: " + getX() + " Y: " + getY());
		this.setRotate(this.getRotate() + moveDistance);
		this.move(moveDistance,0);
	}
	
	public static void setDX(double x) {
    	moveDistance = x;
    }
    
    public static double getDX() {
    	return  moveDistance;
    }
    
    public static double returnOriginal() {
    	return ORIGINAL_DX;
    }
}
