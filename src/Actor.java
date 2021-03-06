import java.util.ArrayList;

import javafx.scene.Node;
import javafx.scene.image.ImageView;
public abstract class Actor extends  ImageView{
    public abstract void act(long now);
    public double getHeight() {
        return this.getLayoutBounds().getHeight();
    }
    public double getWidth() {
        return this.getLayoutBounds().getWidth();
    }
    public void move(double dx, double dy) {
        setX(getX()+dx);
        setY(getY()+dy);
    }
    public World getWorld() {
        if(this.getParent() instanceof World) {
            return (World)getParent();
        }
        return null;
    }
    public <A extends Actor> java.util.List<A> getIntersectingObjects(java.lang.Class<A> cls) {
        ArrayList<A> list = new ArrayList<A>();
        for (Node n: getWorld().getChildren()) {
            if (!n.equals(this) && cls.isInstance(n)) {
                if (n.getBoundsInParent().intersects(getBoundsInParent())) {
                    list.add(cls.cast(n));
                }
            }
        }
        return list;

    }
    public <A extends Node> A getOneIntersectingObject(java.lang.Class<A> cls) {
        for (Node n: getWorld().getChildren()) {
            if (!n.equals(this) && cls.isInstance(n)) {
                if (n.getBoundsInParent().intersects(getBoundsInParent())) {

                    return cls.cast(n);
                }
            }
        }
        return null;
    }
}

