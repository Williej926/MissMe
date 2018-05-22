public class Obstacles extends Actor{
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
        this.setRotate(this.getRotate() + 5);
        this.move(5,0);

    }
}
