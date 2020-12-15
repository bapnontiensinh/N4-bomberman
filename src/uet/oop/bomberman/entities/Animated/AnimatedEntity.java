package uet.oop.bomberman.entities.Animated;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Entity;

import java.util.ArrayList;
import java.util.List;

import static uet.oop.bomberman.BombermanGame.WIDTH;
import static uet.oop.bomberman.graphics.Sprite.SCALED_SIZE;

public abstract class AnimatedEntity extends Entity {
    protected BombermanGame game;

    protected int _animate = 0;
    protected float speed;


    public AnimatedEntity(BombermanGame game, int x, int y, Image img) {
        super(x, y, img);
        this.game = game;
    }

    public AnimatedEntity() {

    }

    protected boolean collisiontoDown() {
        int Y_pos = (int) (bound.getY() + bound.getHeight() + 1) / SCALED_SIZE;
        int X_pos_1 = (int) (bound.getX()) / SCALED_SIZE;
        int X_pos_2 = (int) (bound.getX() + bound.getWidth() - 1) / SCALED_SIZE;
        int index_pos_1 = X_pos_1 + Y_pos * WIDTH;
        int index_pos_2 = X_pos_2 + Y_pos * WIDTH;
        return game.getStillObjects().get(index_pos_1).isSolid() || game.getStillObjects().get(index_pos_2).isSolid();
    }

    protected boolean collisiontoRight() {
        int X_pos = (int) (bound.getX() + bound.getWidth() + 1) / SCALED_SIZE;
        int Y_pos_1 = (int) (bound.getY()) / SCALED_SIZE;
        int Y_pos_2 = (int) (bound.getY() + bound.getHeight() - 1) / SCALED_SIZE;
        int index_pos_1 = X_pos + Y_pos_1 * WIDTH;
        int index_pos_2 = X_pos + Y_pos_2 * WIDTH;
        return game.getStillObjects().get(index_pos_1).isSolid() || game.getStillObjects().get(index_pos_2).isSolid();
    }

    protected boolean collisiontoLeft() {
        int X_pos = (int) (bound.getX() - 1) / SCALED_SIZE;
        int Y_pos_1 = (int) (bound.getY()) / SCALED_SIZE;
        int Y_pos_2 = (int) (bound.getY() + bound.getHeight() - 1) / SCALED_SIZE;
        int index_pos_1 = X_pos + Y_pos_1 * WIDTH;
        int index_pos_2 = X_pos + Y_pos_2 * WIDTH;
        return game.getStillObjects().get(index_pos_1).isSolid() || game.getStillObjects().get(index_pos_2).isSolid();
    }

    protected boolean collisiontoUp() {
        int Y_pos = (int) (bound.getY() - 1) / SCALED_SIZE;
        int X_pos_1 = (int) (bound.getX()) / SCALED_SIZE;
        int X_pos_2 = (int) (bound.getX() + bound.getWidth() - 1) / SCALED_SIZE;
        int index_pos_1 = X_pos_1 + Y_pos * WIDTH;
        int index_pos_2 = X_pos_2 + Y_pos * WIDTH;
        return game.getStillObjects().get(index_pos_1).isSolid() || game.getStillObjects().get(index_pos_2).isSolid();
    }

    /**
     * Test collision class by unit
     *
     * @return
     */
    public List<Integer> collision() {

        List<Integer> collision_direction = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            int index = caculate(i, getX_center_unit(), getY_center_unit());
            if (!game.getStillObjects().get(index).isSolid()) {
                collision_direction.add(i);
            }else{
                collision_direction.add(-1);
            }
        }
        return collision_direction;
    }

    public int caculate(int direction, int x, int y) {
        switch (direction) {
            case 0:
                y--;
                break;
            case 1:
                x--;
                break;
            case 2:
                y++;
                break;
            case 3:
                x++;
                break;
        }
        return y * WIDTH + x;
    }

    public void animate() {
        if (_animate < 500)
            _animate++;
        else
            _animate = 0;
    }

    public abstract void createBound();

    public abstract void move();

    public abstract void remove();

}
