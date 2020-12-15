package uet.oop.bomberman.entities.bomb;

import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Animated.AnimatedEntity;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

import java.awt.*;

import static uet.oop.bomberman.graphics.Sprite.SCALED_SIZE;

public class directionalExplosion extends AnimatedEntity {
    protected Explosion[] explosions;
    protected int xOrigin, yOrigin; //??
    protected int direction;
    protected int length = 1;

    public directionalExplosion(BombermanGame game, int x, int y, int direction, int length) {
        super(game,x, y, null);
        this.direction = direction;
        xOrigin = x;
        yOrigin = y;
        this.length = length;

        explosions = new Explosion[length];

        createExplosion();
        createBound();
    }

    public void createExplosion() {
        boolean last = false;

        for (int i = 0; i < explosions.length; ++i) {
            if (i == explosions.length - 1) last = true;

            switch (direction) {
                case 0: y--; break;
                case 1: x-- ; break;
                case 2: y++ ; break;
                case 3: x++ ; break;
            }
            explosions[i] = new Explosion(x , y , last, direction);
        }
    }

    @Override
    public void update() {

    }

    @Override
    public void render(GraphicsContext gc) {
        for (int i = 0; i < explosions.length; i++) {
            explosions[i].render(gc);
         //   gc.fillRect(bound.getX(), bound.getY(), bound.getWidth(), bound.getHeight());
        }
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

    }

    public void setLength(int length) {
        this.length = length;
    }
}
