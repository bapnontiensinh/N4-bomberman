package uet.oop.bomberman.entities.Animated;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.AnimatedEntity;

import java.util.Random;

import static uet.oop.bomberman.graphics.Sprite.SCALED_SIZE;


public abstract class Enemy extends AnimatedEntity {
    protected long time_start = System.currentTimeMillis();
    protected int direction = randomNumber(-1);
    protected int tmp = direction;
    protected int time = 1500;
    protected int _x = 0;
    protected int _y = 0;

    public Enemy(BombermanGame game, int x, int y, Image img) {
        super(game, x, y, img);
        active = true;
    }

    @Override
    public void update() {
        animate();
        move();
        kill();
        die();
    }

    @Override
    public void render(GraphicsContext gc) {
        gc.drawImage(img, x_real, y_real);
    }

    @Override
    public void createBound() {
        bound.setWidth(SCALED_SIZE);
        bound.setHeight(SCALED_SIZE);
        bound.setX(x_real);
        bound.setY(y_real);
    }

    /**
     * Javadoc
     */
    protected int randomNumber(int except) {
        Random random = new Random();
        int random_number = except;
        while (random_number == except) {
            random_number = random.nextInt(4);
        }
        return random_number;
    }

    protected void kill() {
        if (bound.intersects(game.player.bound.getX(), game.player.bound.getY(),
                game.player.bound.getWidth(), game.player.bound.getHeight())) {
            //Die
            System.out.println("You Die");
            game.player.setActive(false);
        }
    }

    protected abstract void die();
}
