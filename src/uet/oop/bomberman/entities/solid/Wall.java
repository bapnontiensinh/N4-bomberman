package uet.oop.bomberman.entities.solid;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;

public class Wall extends Entity {
    public Wall(int x, int y, Image img) {
        super(x, y, img);
        solid = true;

    }

    @Override
    public void update() {

    }

}
