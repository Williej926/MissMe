import java.util.ArrayList;
import java.util.List;

import javafx.animation.AnimationTimer;
import javafx.scene.Node;
public class GameWorld extends World{
    private static long prev = 0;
    private static long diff = (long) (5*1e9);
    public GameWorld(int width) {
        this.setWidth(width);
    }
    public void act(long now) {

        System.out.println(getChildren());
        System.out.println("Width: " + getWidth());
        ArrayList<Node> deletes = new ArrayList<Node>();
        for (Node n : getChildren()) {
            if (n instanceof Actor){
                System.out.println("X: " + ((Actor) n).getX());
                if (((Actor) n).getX() > getWidth()) {
                    //System.out.println("deleted");
                    //((Actor) n).getWorld().remove(((Actor) n));
                    deletes.add(n);
                } else if (((Actor) n).getX() < 0) {
                    //System.out.println("deleted");
                    //((Actor) n).getWorld().remove(((Actor) n));


                    deletes.add(n);
                }
            }
        }
        for(Node n:deletes) {
            if(n instanceof Actor) {
                System.out.println("Deleted");
                ((Actor) n).getWorld().remove(((Actor) n));
            }
        }
        if (now-prev>=diff) {
            double randY = Math.random()*getHeight();
            boolean side = Math.random()<0.5;
            if(side) {
                
            }
            //do something
            prev = now;
        }
    }
}
