package uet.oop.bomberman.entities.Animated;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.AnimatedEntity;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.graphics.Sprite.SCALED_SIZE;

public class Brick extends AnimatedEntity {
    public boolean removed = false;

    public Brick(BombermanGame game, int x, int y, Image img) {
        super(game, x, y, img);
        solid = true;
        createBound();
        active = true;

    }

    @Override
    public void update() {
        animate();
        remove();

    }

    @Override
    public void createBound() {
        bound.setWidth(SCALED_SIZE);
        bound.setHeight(SCALED_SIZE);
        bound.setX(x_real);
        bound.setY(y_real);
    }

    @Override
    public void move() {

    }

    @Override
    public void remove() {
        if (!active) {
            this.img = Sprite.movingSprite(Sprite.brick_exploded, Sprite.brick_exploded1, Sprite.brick_exploded2, _animate, 100).getFxImage();
            removed = true;
        }
    }
}

