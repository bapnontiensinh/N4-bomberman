package uet.oop.bomberman.entities.Animated;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Animated.AnimatedEntity;
import uet.oop.bomberman.entities.bomb.Bomb;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.graphics.Sprite.SCALED_SIZE;

//Cài đặt moving và khắc phục lỗi bị khựng khi thay đổi speed.
public class Bomber extends AnimatedEntity {
    public Bomber(BombermanGame game, int x, int y, Image img) {
        super(game, x, y, img);
        speed = 1;
    }

    // Can mo rong cho 1 bound
    @Override
    public void move() {
        createBound();
        if (game.getKeyBoard().up) {
            if (!collisiontoUp()) {
                this.img = Sprite.movingSprite(Sprite.player_up, Sprite.player_up_1,
                        Sprite.player_up_2, _animate, 20).getFxImage();
                y -= speed;

            }

        }
        if (game.getKeyBoard().down) {
            if (!collisiontoDown()) {
                this.img = Sprite.movingSprite(Sprite.player_down, Sprite.player_down_1,
                        Sprite.player_down_2, _animate, 20).getFxImage();

                y += speed;
            }
        }
        if (game.getKeyBoard().left) {
            if (!collisiontoLeft()) {
                this.img = Sprite.movingSprite(Sprite.player_left, Sprite.player_left_1,
                        Sprite.player_left_2, _animate, 20).getFxImage();
                x -= speed;
            }
        }
        if (game.getKeyBoard().right) {
            if (!collisiontoRight()) {
                this.img = Sprite.movingSprite(Sprite.player_right, Sprite.player_right_1,
                        Sprite.player_right_2, _animate, 20).getFxImage();
                x += speed;
            }
        }
        if (game.getKeyBoard().space){
            createBomb();
        }
    }

    private int numberOfBomb=1;

    private void createBomb() {
        Bomb bomb = new Bomb((int)bound.getX(),(int)bound.getY(),Sprite.bomb.getFxImage());

    }


    @Override
    public void update() {
        animate();
        move();
        kill();
    }

    private void kill() {

    }

    @Override
    public void render(GraphicsContext gc) {
        gc.drawImage(img, x, y);
//        gc.fillRect(bound.getX(), bound.getY(), bound.getWidth(), bound.getHeight());
    }

    @Override
    public void createBound() {
//        bound.setWidth(DEFAULT_SIZE);
//        bound.setHeight(SCALED_SIZE * 3 / 4);
//        bound.setX(x + SCALED_SIZE / 2 - bound.getWidth() / 2 - 6);
//        bound.setY(y + SCALED_SIZE * 1 / 8);

        bound.setWidth(SCALED_SIZE-6);
        bound.setHeight(SCALED_SIZE-6);
        bound.setX(x+6);
        bound.setY(y+6);

//        bound.setWidth(SCALED_SIZE);
//        bound.setHeight(SCALED_SIZE);
//        bound.setX(x);
//        bound.setY(y);
    }
}
