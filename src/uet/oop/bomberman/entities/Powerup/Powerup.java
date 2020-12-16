package uet.oop.bomberman.entities.Powerup;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.AnimatedEntity;

public abstract class Powerup extends AnimatedEntity {
    protected boolean active = false;
    protected int level;
    private boolean removed = false;

    public Powerup(BombermanGame game, int x, int y, int level, Image img) {
        super(game, x, y, img);
        this.level = level;
    }

    /**
     * Javadoc
     */
    public void activate() {
        if (!active) {
            active = true;
            removed = true;
            game.player.addPowerup(this);
        }
    }

    public abstract void setValue();

    @Override
    public void update() {
        if (collisionWithPlayer()) activate();
    }

    @Override
    public void move() {

    }

    @Override
    public void remove() {

    }

    /**
     * Javadoc
     */
    protected boolean collisionWithPlayer() {
        return game.player.getX_center_unit() == this.x && game.player.getY_center_unit() == this.y;
    }

    /**
     * getter & setter
     */
    public boolean isRemoved() {
        return removed;
    }

    @Override
    public boolean isActive() {
        return active;
    }

    @Override
    public void setActive(boolean active) {
        this.active = active;
    }
}
