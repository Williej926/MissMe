public class Obstacles extends Circle{
    private static double moveDistance = 5;
    private static final double ORIGINAL_DX = 5;


	public Obstacles() {
        this.setRotate(0);
        this.setScaleX(0.5);
		this.setScaleY(0.5);

    }
    public Obstacles(Double angle,Double x, Double y) {
            this.setRotate(angle);
            this.setX(x);
            this.setY(y);
            this.setScaleX(0.5);
    		this.setScaleY(0.5);
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
