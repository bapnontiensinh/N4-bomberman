package uet.oop.bomberman.entities.Animated;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Powerup.Powerup;

import static uet.oop.bomberman.graphics.Sprite.SCALED_SIZE;

public class Brick extends AnimatedEntity {
    private Powerup containPowerup = null;

    public Brick(BombermanGame game, int x, int y, Image img, Powerup powerup) {
        super(game, x, y, img);
        solid = true;
        createBound();
        active = true;
        this.containPowerup = powerup;
    }

    @Override
    public void update() {
        //  remove();
    }

    @Override
    public void createBound() {
        bound.setWidth(SCALED_SIZE);
        bound.setHeight(SCALED_SIZE);
        bound.setX(x_real);
        bound.setY(y_real);
    }

//    public boolean isActive() {
//        return active;
//    }

    @Override
    public void move() {

    }

    @Override
    public void remove() { // die
        // active=false;
    }

    public Powerup getContainPowerup() {
        return containPowerup;
    }

    public void setContainPowerup(Powerup containPowerup) {
        this.containPowerup = containPowerup;
    }
}
