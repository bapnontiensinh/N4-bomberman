package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;

public class Portal extends AnimatedEntity {
    public Portal(BombermanGame game, int x, int y, Image img) {
        super(game, x, y, img);
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
    public void activate() {
        game.setCurrentLevel(game.getCurrentLevel() + 1);
        game.createMap(game.getCurrentLevel());
    }

    /**
     * Javadoc
     */
    protected boolean collisionWithPlayer() {
        return game.player.getX_center_unit() == this.x && game.player.getY_center_unit() == this.y;
    }

    @Override
    public void update() {
        if (collisionWithPlayer() && game.getNumEnemy() == 0) activate();
    }
}
