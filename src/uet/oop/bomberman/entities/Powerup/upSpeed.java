package uet.oop.bomberman.entities.Powerup;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;

public class upSpeed extends Powerup {
    public upSpeed(BombermanGame game, int x, int y, int level, Image img) {
        super(game, x, y, level, img);
    }

    @Override
    public boolean isActive() {
        return super.isActive();
    }

    @Override
    public void remove() {
        super.remove();
    }


    @Override
    public void setValue() {
        game.player.upSpeed();
    }
}
