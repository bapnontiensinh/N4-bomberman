package uet.oop.bomberman.entities.Powerup;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;

public class upBomb extends Powerup {
    public upBomb(BombermanGame game, int x, int y, int level, Image img) {
        super(game, x, y, level, img);
    }

    @Override
    public void setValue() {
        game.player.addBomb();
    }
}
