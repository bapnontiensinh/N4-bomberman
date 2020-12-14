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
    int xOrigin, yOrigin;
    int direction;

    public directionalExplosion(BombermanGame game, int x, int y, int direction) {
        super(game,x, y, null);
        this.direction = direction;
        xOrigin = x;
        yOrigin = y;

        explosions = new Explosion[1];

        createExplosion();
    }

    public void createExplosion() {
        boolean last = false;

        for (int i = 0; i < explosions.length; ++i) {
            if (i == explosions.length - 1) last = true;

            switch (direction) {
                case 0: y-= Sprite.SCALED_SIZE; break;
                case 1: x+= Sprite.SCALED_SIZE; break;
                case 2: y+= Sprite.SCALED_SIZE; break;
                case 3: x-= Sprite.SCALED_SIZE; break;
            }
            explosions[i] = new Explosion(x / Sprite.SCALED_SIZE, y / Sprite.SCALED_SIZE, last, direction);
        }
    }

    @Override
    public void update() {

    }

    @Override
    public void render(GraphicsContext gc) {
        for (int i = 0; i < explosions.length; i++) {
            explosions[i].render(gc);
            gc.fillRect(bound.getX(), bound.getY(), bound.getWidth(), bound.getHeight());
        }
    }

    @Override
    public void createBound() {
        bound.setWidth(SCALED_SIZE - 6);
        bound.setHeight(SCALED_SIZE - 6);
        bound.setX(x + 6);
        bound.setY(y + 6);
    }

    @Override
    public void move() {

    }
}
