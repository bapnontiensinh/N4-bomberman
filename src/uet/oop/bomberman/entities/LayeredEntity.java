package uet.oop.bomberman.entities;

import java.util.ArrayList;
import java.util.List;

public class LayeredEntity extends Entity{
    protected List<Entity> hiddenEntities = new ArrayList<>();

    public LayeredEntity(int x, int y, Entity ... entities) {
        this.x = x;
        this.y = y;

        for (int i = 0; i < entities.length; ++i) {
            hiddenEntities.add(entities[i]);
        }
    }

    @Override
    public void update() {

    }
}
