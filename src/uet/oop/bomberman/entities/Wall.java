package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Animated.AnimatedEntity;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.BombermanGame.WIDTH;
import static uet.oop.bomberman.graphics.Sprite.SCALED_SIZE;
import static uet.oop.bomberman.graphics.Sprite.bomb;

public class Wall extends Entity
{
    public Wall(int x, int y, Image img) {
        super(x, y, img);
        isSolid=true;

    }

    @Override
    public void update() {

    }

}
