package uet.oop.bomberman.entities.Powerup;

import uet.oop.bomberman.BombermanGame;
import javafx.scene.image.Image;
import uet.oop.bomberman.entities.AnimatedEntity;

import static uet.oop.bomberman.graphics.Sprite.SCALED_SIZE;

public abstract class Powerup extends AnimatedEntity {
    protected int duration = -1;
    protected boolean active = false;
    protected int level;
    private boolean removed = false;

    public Powerup (BombermanGame game, int x, int y, int level, Image img) {
        super(game, x, y, img);
        this.level = level;
    }

    public Powerup() {
    }

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
    public boolean isActive() {
        return active;
    }

    public int getDuration() {
        return duration;
    }

    public int getLevel() {
        return level;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public void setActive(boolean active) {
        this.active = active;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public void move() {

    }

    @Override
    public void remove() {

    }

    protected boolean collisionWithPlayer() {
        return game.player.getX_center_unit() == this.x && game.player.getY_center_unit() == this.y;
    }

    public boolean isRemoved() {
        return removed;
    }
}