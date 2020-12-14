package uet.oop.bomberman.entities.bomb;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Animated.AnimatedEntity;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.graphics.Sprite.SCALED_SIZE;

public class Bomb extends AnimatedEntity {
    public int afterExplode = 20; // time to explosion disappear
    public boolean exploded = false;
    public boolean finished =false;
    public directionalExplosion[] explosions = null;
    protected double timeToExplode = 120; // 2 seconds

    public Bomb(BombermanGame game, int x, int y, Image img) {
        super(game,x, y, img);
        isSolid=true;
    }

    public Bomb() {

    }

    @Override
    public void update() {
        createBound();
        if (timeToExplode > 0) {
            --timeToExplode;
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
            explosions[i] = new directionalExplosion(game,x / Sprite.SCALED_SIZE, y / Sprite.SCALED_SIZE, i);
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
                    if (!collisiontoUp()){
                        explosions[0].render(gc);
                    }
                    if (!collisiontoDown()){
                        explosions[2].render(gc);
                    }
                    if (!collisiontoLeft()){
                        explosions[1].render(gc);
                    }
                    if (!collisiontoRight()){
                        explosions[3].render(gc);
                    }
                //    explosions[i].render(gc);
                }
            }
            gc.fillRect(bound.getX(), bound.getY(), bound.getWidth(), bound.getHeight());
        }
        super.render(gc);

    }

    public void remove() {
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
}
