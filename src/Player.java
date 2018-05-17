import javafx.scene.image.ImageView;

import java.util.ArrayList;

public class Player extends Actor{
    ArrayList<Actor> delete =  new ArrayList<>();
    @Override
    public void act(long now) {
        delete = new ArrayList<>();
        Actor obstacle  = this.getOneIntersectingObject(Obstacles.class);
        if(obstacle != null) {
            if(obstacle.getClass() == Obstacles.class) {
                System.out.println("OBSTACLE GANGGG");
                delete.add(obstacle);
            }
        }
    }

    public ArrayList<Actor> getDelete() {
        return delete;

    }
}
