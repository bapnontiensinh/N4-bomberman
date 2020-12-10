package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

import java.util.Random;

import static uet.oop.bomberman.graphics.Sprite.SCALED_SIZE;


public class Enemy extends AnimatedEntity {

    private final boolean stop;
    long time_start = System.currentTimeMillis();
    int direction = randomNumber(-1);
    int tmp = direction;
    int time = 1500;
    int current_x;
    int current_y;

    public Enemy(BombermanGame game, int x, int y, Image img) {
        super(game, x, y, img);
        speed = 1;
        stop = true;
    }

    @Override
    public void update() {
        animate();
        move();
        kill();
    }

    @Override
    public void render(GraphicsContext gc) {
        gc.drawImage(img, x, y);
        //    gc.fillRect(bound.getX(), bound.getY(), bound.getWidth(), bound.getHeight());
    }

    @Override
    public void createBound() {
        bound.setWidth(SCALED_SIZE);
        bound.setHeight(SCALED_SIZE);
        bound.setX(x);
        bound.setY(y);

//        bound.setWidth(DEFAULT_SIZE);
//        bound.setHeight(SCALED_SIZE * 3 / 4);
//        bound.setX(x + SCALED_SIZE / 2 - bound.getWidth() / 2 - 6);
//        bound.setY(y + SCALED_SIZE * 1 / 8);
    }

    private int randomNumber(int except) {
        Random random = new Random();
        int random_number = except;
        while (random_number == except) {
            random_number = random.nextInt(4);
        }
        return random_number;
    }

    private void moveUp() {
        this.img = Sprite.movingSprite(Sprite.balloom_left1, Sprite.balloom_right1, _animate, 20).getFxImage();
        y -= speed;
        //   System.out.println("up");
    }

    private void moveDown() {
        this.img = Sprite.movingSprite(Sprite.balloom_left1, Sprite.balloom_right1, _animate, 20).getFxImage();
        y += speed;
        //  System.out.println("down");
    }

    private void moveLeft() {
        this.img = Sprite.movingSprite(Sprite.balloom_left1, Sprite.balloom_left2,
                Sprite.balloom_left3, _animate, 20).getFxImage();
        x -= speed;
        //    System.out.println("left");
    }

    private void moveRight() {
        this.img = Sprite.movingSprite(Sprite.balloom_right1, Sprite.balloom_right2,
                Sprite.balloom_right3, _animate, 20).getFxImage();
        x += speed;
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
                }
                break;
            case 1: //LEFT

                if (!collisiontoLeft()) {

                    moveLeft();

                } else {
                    tmp = direction;
                    direction = randomNumber(1);
                    time_start = System.currentTimeMillis();
                }
                break;
            case 2: //DOWN

                if (!collisiontoDown()) {

                    moveDown();
                } else {
                    tmp = direction;
                    direction = randomNumber(2);
                    time_start = System.currentTimeMillis();
                }
                break;
            case 3:// RIGHT

                if (!collisiontoRight()) {

                    moveRight();
                } else {
                    tmp = direction;
                    direction = randomNumber(3);
                    time_start = System.currentTimeMillis();
                }
                break;
        }
        long end = System.currentTimeMillis();

        if ((end - time_start) > time) {
            tmp = direction;
            direction = randomNumber(direction);
            time_start = System.currentTimeMillis();
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>.");
        }
    }

    private void kill() {
        if (bound.intersects(game.player.bound.getX(), game.player.bound.getY(),
                game.player.bound.getWidth(), game.player.bound.getHeight())) {
            //Die
            System.out.println("Die");
        }
    }
}