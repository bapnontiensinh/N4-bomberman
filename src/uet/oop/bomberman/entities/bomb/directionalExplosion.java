package uet.oop.bomberman.entities.bomb;

import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.AnimatedEntity;

import static uet.oop.bomberman.graphics.Sprite.SCALED_SIZE;

public class directionalExplosion extends AnimatedEntity {
    protected Explosion[] explosions;
    int xOrigin, yOrigin; //??
    int direction;

    public directionalExplosion(BombermanGame game, int x, int y, int direction) {
        super(game, x, y, null);
        this.direction = direction;
        xOrigin = x;
        yOrigin = y;

        explosions = new Explosion[1];

        createExplosion();
        createBound();
    }

    public void createExplosion() {
        boolean last = false;

        for (int i = 0; i < explosions.length; ++i) {
            if (i == explosions.length - 1) last = true;

            switch (direction) {
                case 0:
                    y--;
                    break;
                case 1:
                    x--;
                    break;
                case 2:
                    y++;
                    break;
                case 3:
                    x++;
                    break;
            }
            explosions[i] = new Explosion(game, x, y, last, direction);
        }
    }

    @Override
    public void update() {
        for (int i = 0; i < explosions.length; i++) {
            explosions[i].update();
            //   gc.fillRect(bound.getX(), bound.getY(), bound.getWidth(), bound.getHeight());
        }
    }

    @Override
    public void render(GraphicsContext gc) {

        for (int i = 0; i < explosions.length; i++) {
            explosions[i].render(gc);
//         //   gc.fillRect(bound.getX(), bound.getY(), bound.getWidth(), bound.getHeight());
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
}
