package uet.oop.bomberman.entities.bomb;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

public class Explosion extends Entity {
    protected boolean last = false;

    public Explosion(int x, int y,boolean last, int direction) {
        super(x,y,null);

        switch (direction) {
            case 0:
            if(last ==  false) {
                img = Sprite.explosion_vertical2.getFxImage();
            } else {
                img = Sprite.explosion_vertical_top_last2.getFxImage();
            }
            break;
            case 1:
            if(last == false) {
                img = Sprite.explosion_horizontal2.getFxImage();
            } else {
                img = Sprite.explosion_horizontal_right_last2.getFxImage();
            }
            break;
            case 2:
            if(last == false) {
                img = Sprite.explosion_vertical2.getFxImage();
            } else {
                img = Sprite.explosion_vertical_down_last2.getFxImage();
            }
            break;
            case 3:
            if(last == false) {
                img = Sprite.explosion_horizontal2.getFxImage();
            } else {
                img = Sprite.explosion_horizontal_left_last2.getFxImage();
            }
            break;
        }
    }

    @Override
    public void update() {

    }
}
