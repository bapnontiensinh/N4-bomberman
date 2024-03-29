package uet.oop.bomberman.entities.Animated;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.AnimatedEntity;
import uet.oop.bomberman.entities.Powerup.Powerup;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;

import static uet.oop.bomberman.graphics.Sprite.SCALED_SIZE;

//Cài đặt moving và khắc phục lỗi bị khựng khi thay đổi speed.

public class Bomber extends AnimatedEntity {
    public static List<Powerup> powerupList = new ArrayList<>();


    public Bomber(BombermanGame game, int x, int y, Image img) {
        super(game, x, y, img);
        speed = 2;
        active = true;
    }

    @Override
    public void move() {
        createBound();
        if (game.getKeyBoard().up) {
            this.img = Sprite.movingSprite(Sprite.player_up, Sprite.player_up_1,
                    Sprite.player_up_2, _animate, 20).getFxImage();
            if (!collisiontoUp()) {

                y_real -= speed;
            }
        }
        if (game.getKeyBoard().down) {
            this.img = Sprite.movingSprite(Sprite.player_down, Sprite.player_down_1,
                    Sprite.player_down_2, _animate, 20).getFxImage();
            if (!collisiontoDown()) {
                y_real += speed;
            }
        }
        if (game.getKeyBoard().left) {
            this.img = Sprite.movingSprite(Sprite.player_left, Sprite.player_left_1,
                    Sprite.player_left_2, _animate, 20).getFxImage();
            if (!collisiontoLeft()) {
                x_real -= speed;
            }
        }
        if (game.getKeyBoard().right) {
            this.img = Sprite.movingSprite(Sprite.player_right, Sprite.player_right_1,
                    Sprite.player_right_2, _animate, 20).getFxImage();
            if (!collisiontoRight()) {
                x_real += speed;
            }
        }
        if (game.getKeyBoard().space) {
            System.out.println(game.bombExisited + " " + game.maxBomb);
            if (game.bombExisited < game.maxBomb) createBomb();
        }
    }

    public void addPowerup(Powerup p) {
        if (!p.isActive()) return;

        powerupList.add(p);

        p.setValue();
    }

    @Override
    public void remove() {

    }

    private void createBomb() {
        game.createBomb();
    }

    @Override
    public void update() {
        animate();
        move();
        die();
    }

    private void die() {
        if (!isActive()) {
            this.img = Sprite.movingSprite(Sprite.player_dead1, Sprite.player_dead2,
                    Sprite.player_dead3, _animate, 20).getFxImage();
            game.createMap(1);
            game.lives--;
            if (game.lives<=0){
                System.out.println("Game over");
            }
        }
    }

    @Override
    public void render(GraphicsContext gc) {
        gc.drawImage(img, x_real, y_real);
        // gc.fillRect(bound.getX(), bound.getY(), bound.getWidth(), bound.getHeight());
    }

    @Override
    public void createBound() {
        bound.setWidth(SCALED_SIZE - 6);
        bound.setHeight(SCALED_SIZE - 6);
        bound.setX(x_real + 6);
        bound.setY(y_real + 6);
    }

    public void upSpeed() {
        this.speed += 0.2;
        System.out.println(speed);
    }

    public void upExplosion() {
        game.setBomblength(game.getBomblength() + 1);
    }

    public void addBomb() {
        game.maxBomb = 3;
    }
}
