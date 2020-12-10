package uet.oop.bomberman.entities.bomb;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

public class Bomb extends Entity {
    protected double timeToExplode = 10; // 2 seconds
    public int afterExplode = 20; // time to explosion disappear

    boolean exploded = false;
    public directionalExplosion[] explosions = null;

    public Bomb(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {
        if (timeToExplode > 0) {
            --timeToExplode;
            System.out.print(timeToExplode);
        } else {
            if (!exploded) {
                explosion();
            } else {
                updateExplosion();
            }
            if (afterExplode > 0) {
                --afterExplode;
            } else {
                System.out.print("remove");
            }
        }
    }

    public void explosion() {
        exploded = true;

        explosions = new directionalExplosion[4];

        for (int i = 0; i < 4; ++i) {
            explosions[i] = new directionalExplosion(x, y, i);
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

    public void remove() {

    }
}
