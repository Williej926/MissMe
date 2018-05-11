import java.util.ArrayList;
import java.util.List;

import javafx.animation.AnimationTimer;
import javafx.scene.Node;
public class GameWorld extends World{
    @Override
    public void act(long now) {
        System.out.println(getChildren());
        ArrayList<Node> deletes = new ArrayList();
        for (Node n : getChildren()) {
            if (n instanceof Actor){
                if (((Actor) n).getX() > ((Actor) n).getWorld().getWidth()) {
                    //System.out.println("deleted");
                    //((Actor) n).getWorld().remove(((Actor) n));
                    deletes.add(((Actor) n));
                } else if (((Actor) n).getX() < 0) {
                    //System.out.println("deleted");
                    //((Actor) n).getWorld().remove(((Actor) n));
                    deletes.add(((Actor) n));
                }
            }
        }
        for(Node n:deletes) {
            if(n instanceof Actor) {
                System.out.println("Deleted");
                ((Actor) n).getWorld().remove(((Actor) n));
            }
        }

    }
}
