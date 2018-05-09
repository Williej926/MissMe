import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
public class GameWorld extends World{
    @Override
    public void act(long now) {
        for (Node n : getChildren()) {
            if (n instanceof Actor){
                if (n.getX() > getWorld().getWidth()) {
                    System.out.println("deleted");
                    getWorld().remove(n);
                } else if (n.getX() < 0) {
                    System.out.println("deleted");
                    getWorld().remove(n);
                }
            }
        }
    }
}
