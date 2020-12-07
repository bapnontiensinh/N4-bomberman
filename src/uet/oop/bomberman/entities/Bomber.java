package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

//Cài đặt moving và khắc phục lỗi bị khựng khi thay đổi speed.
public class Bomber extends Entity {
    public static final int DEFAULT_SIZE = 16;
    public static final int SCALED_SIZE = DEFAULT_SIZE * 2;
    public BombermanGame game;
    public int _animate = 0;
    private boolean _moving;
    private int speed = 1;

    public Bomber(BombermanGame game, int x, int y, Image img) {
        super(x, y, img);
        this.game = game;


    }

    // Can mo rong cho 1 bound

    public void move() {
        bound.setWidth(DEFAULT_SIZE);
        bound.setHeight(SCALED_SIZE * 3 / 4);
        bound.setX(x + SCALED_SIZE / 2 - bound.getWidth() / 2 - 6);
        bound.setY(y + SCALED_SIZE * 1 / 8);


        if (game.getKeyBoard().up) {
            if (!collisiontoUp(x, y)) {
                this.img = Sprite.movingSprite(Sprite.player_up, Sprite.player_up_1,
                        Sprite.player_up_2, _animate, 20).getFxImage();
                y -= speed;

            }

        }
        if (game.getKeyBoard().down) {
            if (!collisiontoDown(x, y)) {
                this.img = Sprite.movingSprite(Sprite.player_down, Sprite.player_down_1,
                        Sprite.player_down_2, _animate, 20).getFxImage();

                y += speed;
            }
        }
        if (game.getKeyBoard().left) {
            if (!collisiontoLeft(x, y)) {
                this.img = Sprite.movingSprite(Sprite.player_left, Sprite.player_left_1,
                        Sprite.player_left_2, _animate, 20).getFxImage();
                x -= speed;
            }
        }
        if (game.getKeyBoard().right) {
            if (!collisiontoRight(x, y)) {
                this.img = Sprite.movingSprite(Sprite.player_right, Sprite.player_right_1,
                        Sprite.player_right_2, _animate, 20).getFxImage();
                x += speed;
            }
        }
    }

    private boolean collisiontoDown(int x, int y) {
        int Y_pos = (int) (bound.getY() + bound.getHeight() + 1) / SCALED_SIZE;
        int X_pos_1 = (int) (bound.getX()) / SCALED_SIZE;
        int X_pos_2 = (int) (bound.getX() + bound.getWidth()) / SCALED_SIZE;
        int index_pos_1 = X_pos_1 + Y_pos * game.WIDTH;
        int index_pos_2 = X_pos_2 + Y_pos * game.WIDTH;
        if (game.getStillObjects().get(index_pos_1).isSolid || game.getStillObjects().get(index_pos_2).isSolid) { //right
            return true;
        } else
            return false;
    }

    private boolean collisiontoRight(int x, int y) {
        int X_pos = (int) (bound.getX() + bound.getWidth() + 1) / SCALED_SIZE;
        int Y_pos_1 = (int) (bound.getY()) / SCALED_SIZE;
        int Y_pos_2 = (int) (bound.getY() + bound.getHeight()) / SCALED_SIZE;
        int index_pos_1 = X_pos + Y_pos_1 * game.WIDTH;
        int index_pos_2 = X_pos + Y_pos_2 * game.WIDTH;
        if (game.getStillObjects().get(index_pos_1).isSolid || game.getStillObjects().get(index_pos_2).isSolid) { //right
            return true;
        } else
            return false;
    }

    private boolean collisiontoLeft(int x, int y) {
        int X_pos = (int) (bound.getX() - 1) / SCALED_SIZE;
        int Y_pos_1 = (int) (bound.getY()) / SCALED_SIZE;
        int Y_pos_2 = (int) (bound.getY() + bound.getHeight()) / SCALED_SIZE;
        int index_pos_1 = X_pos + Y_pos_1 * game.WIDTH;
        int index_pos_2 = X_pos + Y_pos_2 * game.WIDTH;
        //right
        return game.getStillObjects().get(index_pos_1).isSolid || game.getStillObjects().get(index_pos_2).isSolid;
    }

    private boolean collisiontoUp(int x, int y) {
        int Y_pos = (int) (bound.getY() - 1) / SCALED_SIZE;
        int X_pos_1 = (int) (bound.getX()) / SCALED_SIZE;
        int X_pos_2 = (int) (bound.getX() + bound.getWidth()) / SCALED_SIZE;
        int index_pos_1 = X_pos_1 + Y_pos * BombermanGame.WIDTH;
        int index_pos_2 = X_pos_2 + Y_pos * game.WIDTH;
        if (game.getStillObjects().get(index_pos_1).isSolid || game.getStillObjects().get(index_pos_2).isSolid) { //right
            return true;
        } else
            return false;
    }

    public void animate() {
        if (_animate < 500)
            _animate++;
        else
            _animate = 0;
    }

    @Override
    public void update() {
        animate();
        move();
    }

    @Override
    public void render(GraphicsContext gc) {
        gc.drawImage(img, x, y);
        //  gc.fillRect(bound.getX(), bound.getY(), bound.getWidth(), bound.getHeight());
    }
}
