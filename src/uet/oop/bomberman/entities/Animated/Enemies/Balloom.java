package uet.oop.bomberman.entities.Animated.Enemies;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Animated.Enemy;
import uet.oop.bomberman.graphics.Sprite;

public class Balloom extends Enemy {
    public Balloom(BombermanGame game, int x, int y, Image img) {
        super(game, x, y, img);
        speed = 1;
    }
    private void moveUp() {
        this.img = Sprite.movingSprite(Sprite.balloom_left1, Sprite.balloom_right1, _animate, 20).getFxImage();
        y_real -= speed;
        //   System.out.println("up");
    }

    private void moveDown() {
        this.img = Sprite.movingSprite(Sprite.balloom_left1, Sprite.balloom_right1, _animate, 20).getFxImage();
        y_real += speed;
        //  System.out.println("down");
    }

    private void moveLeft() {
        this.img = Sprite.movingSprite(Sprite.balloom_left1, Sprite.balloom_left2,
                Sprite.balloom_left3, _animate, 20).getFxImage();
        x_real -= speed;
        //    System.out.println("left");
    }

    private void moveRight() {
        this.img = Sprite.movingSprite(Sprite.balloom_right1, Sprite.balloom_right2,
                Sprite.balloom_right3, _animate, 20).getFxImage();
        x_real += speed;
        //  System.out.println("right");

    }

    @Override
    public void move() {
        createBound();
        switch (direction) {
            case 0: //UP
                if (!collisiontoUp()) {
                    moveUp();
                } else {
                    tmp = direction;
                    direction = randomNumber(0);
                    time_start = System.currentTimeMillis();
                    _y--;
                }
                break;
            case 1: //LEFT

                if (!collisiontoLeft()) {

                    moveLeft();

                } else {
                    tmp = direction;
                    direction = randomNumber(1);
                    time_start = System.currentTimeMillis();
                    _x--;
                }
                break;
            case 2: //DOWN

                if (!collisiontoDown()) {

                    moveDown();
                } else {
                    tmp = direction;
                    direction = randomNumber(2);
                    time_start = System.currentTimeMillis();
                    _y++;
                }
                break;
            case 3:// RIGHT

                if (!collisiontoRight()) {

                    moveRight();
                } else {
                    tmp = direction;
                    direction = randomNumber(3);
                    time_start = System.currentTimeMillis();
                    _x++;
                }
                break;
        }
        long end = System.currentTimeMillis();

        if ((end - time_start) > time) {
            tmp = direction;
            direction = randomNumber(direction);
            time_start = System.currentTimeMillis();
            //System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>.");
        }
    }

    @Override
    public void remove() {

    }

    public void die() {
        // Cần delay trước khi remove
        if (!active) {
            this.img = Sprite.movingSprite(Sprite.balloom_dead, Sprite.balloom_dead, _animate, 40).getFxImage();
        }
    }
}
