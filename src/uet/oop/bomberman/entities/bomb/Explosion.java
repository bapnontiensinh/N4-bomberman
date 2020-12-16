package uet.oop.bomberman.entities.bomb;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.AnimatedEntity;
import uet.oop.bomberman.graphics.Sprite;

public class Explosion extends AnimatedEntity {
    protected boolean last = false;

    //add
    protected int direction;

    public Explosion(BombermanGame game, int x, int y, boolean last, int direction) {
        super(game, x, y, null);
        //chưa có animated

        this.direction = direction;
        this.last = last;

        _animate = 0;
    }

    @Override
    public void update() {

        animate();
        move();
    }

    @Override
    public void createBound() {

    }

    @Override
    public void move() {
        switch (direction) {
            case 0:
                if (last == false) {
                    this.img = Sprite.movingSprite(Sprite.explosion_vertical2, Sprite.explosion_vertical1,
                            Sprite.explosion_vertical, _animate, 160).getFxImage();
                } else {
                    img = Sprite.explosion_vertical_top_last2.getFxImage();
                    this.img = Sprite.movingSprite(Sprite.explosion_vertical_top_last2, Sprite.explosion_vertical_top_last1,
                            Sprite.explosion_vertical_top_last, _animate, 160).getFxImage();
                }
                break;
            case 1:
                if (last == false) {
                    //img = Sprite.explosion_horizontal2.getFxImage();
                    this.img = Sprite.movingSprite(Sprite.explosion_horizontal2, Sprite.explosion_horizontal1,
                            Sprite.explosion_horizontal, _animate, 160).getFxImage();
                } else {
                    //img = Sprite.explosion_horizontal_left_last2.getFxImage();
                    this.img = Sprite.movingSprite(Sprite.explosion_horizontal_left_last2, Sprite.explosion_horizontal_left_last1,
                            Sprite.explosion_horizontal_left_last, _animate, 160).getFxImage();
                }
                break;
            case 2:
                if (last == false) {
                    //img = Sprite.explosion_vertical2.getFxImage();
                    this.img = Sprite.movingSprite(Sprite.explosion_vertical_top_last2, Sprite.explosion_vertical_top_last1,
                            Sprite.explosion_vertical_top_last, _animate, 160).getFxImage();
                } else {
                    // img = Sprite.explosion_vertical_down_last2.getFxImage();
                    this.img = Sprite.movingSprite(Sprite.explosion_vertical_down_last2, Sprite.explosion_vertical_down_last1,
                            Sprite.explosion_vertical_down_last, _animate, 160).getFxImage();
                }
                break;
            case 3:
                if (last == false) {
                    //img = Sprite.explosion_horizontal2.getFxImage();
                    this.img = Sprite.movingSprite(Sprite.explosion_horizontal2, Sprite.explosion_horizontal1,
                            Sprite.explosion_horizontal, _animate, 160).getFxImage();
                } else {
                    //img = Sprite.explosion_horizontal_right_last2.getFxImage();
                    this.img = Sprite.movingSprite(Sprite.explosion_horizontal_right_last2, Sprite.explosion_horizontal_right_last1,
                            Sprite.explosion_horizontal_right_last, _animate, 160).getFxImage();
                }
                break;
        }
    }

    @Override
    public void remove() {

    }
}
