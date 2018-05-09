public class Obstacles extends Actor{
    public Obstacles() {
        this.setRotate(0);
    }
    @Override
    public void act(long now) {
        this.setRotate(this.getRotate() + 5);
        this.move(5,0);

    }
}
