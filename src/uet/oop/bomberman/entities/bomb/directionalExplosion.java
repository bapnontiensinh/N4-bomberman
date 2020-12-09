package uet.oop.bomberman.entities.bomb;

import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.entities.Entity;

import java.awt.*;

public class directionalExplosion extends Entity {
    protected Explosion[] explosions;
    int xOrigin, yOrigin;
    int direction;

    public directionalExplosion(int x, int y, int direction) {
        super(x, y, null);
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
                case 0: y--; break;
                case 1: x++; break;
                case 2: y++; break;
                case 3: x--; break;
            }

            explosions[i] = new Explosion(x, y, last, direction);
        }
    }

    @Override
    public void update() {

    }

    @Override
    public void render(GraphicsContext gc) {
        for (int i = 0; i < explosions.length; i++) {
            explosions[i].render(gc);
        }
    }
}
