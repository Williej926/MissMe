public class Obstacles extends Actor{
    public Obstacles() {
        this.setRotate(0);

    }
    public Obstacles(Double angle,Double x, Double y) {
    }
    @Override
    public void act(long now) {
        this.setRotate(this.getRotate() + 5);
        this.move(5,0);

    }
}
