package uet.oop.bomberman.entities.bomb;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Animated.AnimatedEntity;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

public class Bomb extends AnimatedEntity {
    protected double timeToExplode = 120; // 2 seconds
    public int afterExplode = 50; // time to explosion disappear

    boolean exploded = false;
    public directionalExplosion[] explosions = null;
    private boolean removed = false;

    public Bomb(BombermanGame game, int x, int y, Image img) {
        super(game, x, y, img);
    }

    @Override
    public void update() {
        createBound();
        if (timeToExplode > 0) {
            --timeToExplode;
            //System.out.print(timeToExplode);
        } else {
            if (!exploded) {
                explosion();
            } else {
                updateExplosion();
            }
            if (afterExplode > 0) {
                --afterExplode;
            } else {
                remove();
            }
        }
    }

    public void explosion() {
        exploded = true;

        explosions = new directionalExplosion[4];

        for (int i = 0; i < 4; ++i) {
            explosions[i] = new directionalExplosion(x / Sprite.SCALED_SIZE, y / Sprite.SCALED_SIZE, i);
        }
    }

    public void updateExplosion() {

    }

    @Override
    public void render(GraphicsContext gc) {
        if (exploded) {
            img = Sprite.bomb_exploded2.getFxImage();
            for (int i = 0; i < explosions.length; ++i) {
                if (explosions[i] != null) {
                    explosions[i].render(gc);
                }
            }
        }
        super.render(gc);
    }

    @Override
    public void remove() {
        removed = true;
    }

    @Override
    public void createBound() {

    }

    @Override
    public void move() {
        finished=true;
    }

    @Override
    public void createBound() {
        bound.setWidth(SCALED_SIZE);
        bound.setHeight(SCALED_SIZE);
        bound.setX(x);
        bound.setY(y);
    }

    @Override
    public void move() {

    }

    public boolean isRemoved() {
        return removed;
    }
}
