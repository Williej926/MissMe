import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
public abstract class World extends Pane {
    private AnimationTimer timer;

    public World() {
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                act(now);
                for (Node n : getChildren()) {
                    if(n instanceof Actor) {
                        ((Actor) n).act(now);
                    }
                }

            }
        };
    }
    public abstract void act(long now);
    public <A extends Actor> java.util.List<A> getObjects(java.lang.Class<A> cls) {
        ArrayList<A> list = new ArrayList<A>();
        for (Node n: getChildren()) {
            if (cls.isInstance(n)) {
                list.add(cls.cast(n));
            }
        }
        return list;
    }
    public void add(Actor actor) {
        getChildren().addAll(actor);
    }
    public void remove(Actor actor) {
        getChildren().remove(actor);
    }
    public void start() {
        timer.start();
    }
    public void stop() {
        timer.stop();
    }


}