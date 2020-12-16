package uet.oop.bomberman.entities.Animated.Enemies;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Animated.Enemy;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.graphics.Sprite.SCALED_SIZE;

public class Oneal extends Enemy {
    public Oneal(BombermanGame game, int x, int y, Image img) {
        super(game, x, y, img);
        speed=1;
    }
    private void moveUp() {
        this.img = Sprite.movingSprite(Sprite.oneal_left1, Sprite.oneal_right1, _animate, 20).getFxImage();
        tmp_y= y_real;
        y_real -= speed;
        //   System.out.println("up");
    }

    private void moveDown() {
        this.img = Sprite.movingSprite(Sprite.oneal_left1, Sprite.oneal_right1, _animate, 20).getFxImage();
        tmp_y=y_real;
        y_real += speed;
        //  System.out.println("down");
    }

    private void moveLeft() {
        this.img = Sprite.movingSprite(Sprite.oneal_left1, Sprite.oneal_left2,
                Sprite.oneal_left3, _animate, 20).getFxImage();
        tmp_x= x_real;
        x_real -= speed;
        //    System.out.println("left");
    }

    private void moveRight() {
        this.img = Sprite.movingSprite(Sprite.oneal_right1, Sprite.oneal_right2,
                Sprite.oneal_right3, _animate, 20).getFxImage();
        tmp_x= x_real;
        x_real += speed;
        //  System.out.println("right");

    }
    boolean stop=false;
    int tmp_x;
    int tmp_y;
    public void setStop(boolean stop) {
        if (tmp_x-x_real==0&&tmp_y-y_real==0){
            stop=true;
        }
        else{
            stop=false;
        }
    }

    @Override
    public void move() {
        createBound();
        if (game.player.getX_real()<this.getX_real()){
            if (!collisiontoLeft()){
                moveLeft();
                setStop(stop);
                if (stop){
                    moveDown();
                }
            }

        }
        if (game.player.getX_real()>this.getX_real()){
            if (!collisiontoRight()){
                moveRight();
                setStop(stop);
                if (stop){
                    moveUp();
                }
            }

        }
        if (game.player.getY_real()<this.getY_real()){
            if (!collisiontoUp()){
                moveUp();
                setStop(stop);
                if (stop){
                    moveLeft();
                }
            }

        }
        if (game.player.getY_real()>this.getY_real()){
            if (!collisiontoDown()){
                moveDown();
                setStop(stop);
                if (stop){
                    moveRight();
                }
            }

        }
    }

    @Override
    public void createBound() {
        bound.setWidth(SCALED_SIZE - 12);
        bound.setHeight(SCALED_SIZE - 12);
        bound.setX(x_real + 6);
        bound.setY(y_real + 6);
    }

    @Override
    public void remove() {

    }

    @Override
    protected void die() {

    }
}
