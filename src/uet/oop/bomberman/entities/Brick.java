package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Animated.AnimatedEntity;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.BombermanGame.WIDTH;
import static uet.oop.bomberman.graphics.Sprite.SCALED_SIZE;

public class Brick extends AnimatedEntity {
    public Brick(BombermanGame game, int x, int y, Image img) {
        super(game,x, y, img);
        isSolid = true;
        createBound();
        active=true;

    }

    @Override
    public void update() {
      //  remove();
    }

    @Override
    public void createBound() {
        bound.setWidth(SCALED_SIZE);
        bound.setHeight(SCALED_SIZE);
        bound.setX(x);
        bound.setY(y);
    }
    public boolean isActive() {
        return active;
    }
    @Override
    public void move() {

    }

    @Override
    public void remove() { // die
       // active=false;
    }

}
