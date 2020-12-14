package uet.oop.bomberman.entities.Animated;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Entity;

import static uet.oop.bomberman.graphics.Sprite.SCALED_SIZE;

public abstract class  AnimatedEntity extends Entity {
    protected BombermanGame game;

    protected int _animate = 0;
    protected boolean _moving;
    //protected boolean stop;
    protected int speed;

    public AnimatedEntity(BombermanGame game, int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        this.game = game;
    }
    public AnimatedEntity(){

    }
    protected boolean collisiontoDown() {
        //    stop= true;
        int Y_pos = (int) (bound.getY() + bound.getHeight() + 1) / SCALED_SIZE;
        int X_pos_1 = (int) (bound.getX()) / SCALED_SIZE;
        int X_pos_2 = (int) (bound.getX() + bound.getWidth() - 1) / SCALED_SIZE;
        int index_pos_1 = X_pos_1 + Y_pos * BombermanGame.WIDTH;
        int index_pos_2 = X_pos_2 + Y_pos * BombermanGame.WIDTH;
        return game.getStillObjects().get(index_pos_1).isSolid || game.getStillObjects().get(index_pos_2).isSolid;
    }

    protected boolean collisiontoRight() {
        //  stop= true;
        int X_pos = (int) (bound.getX() + bound.getWidth() + 1) / SCALED_SIZE;
        int Y_pos_1 = (int) (bound.getY()) / SCALED_SIZE;
        int Y_pos_2 = (int) (bound.getY() + bound.getHeight() - 1) / SCALED_SIZE;
        int index_pos_1 = X_pos + Y_pos_1 * BombermanGame.WIDTH;
        int index_pos_2 = X_pos + Y_pos_2 * BombermanGame.WIDTH;
        return game.getStillObjects().get(index_pos_1).isSolid || game.getStillObjects().get(index_pos_2).isSolid;
    }

    protected boolean collisiontoLeft() {
        //stop= true;
        int X_pos = (int) (bound.getX() - 1) / SCALED_SIZE;
        int Y_pos_1 = (int) (bound.getY()) / SCALED_SIZE;
        int Y_pos_2 = (int) (bound.getY() + bound.getHeight() - 1) / SCALED_SIZE;
        int index_pos_1 = X_pos + Y_pos_1 * BombermanGame.WIDTH;
        int index_pos_2 = X_pos + Y_pos_2 * BombermanGame.WIDTH;
        return game.getStillObjects().get(index_pos_1).isSolid || game.getStillObjects().get(index_pos_2).isSolid;
    }

    protected boolean collisiontoUp() {
        //stop= true;
        int Y_pos = (int) (bound.getY() - 1) / SCALED_SIZE;
        int X_pos_1 = (int) (bound.getX()) / SCALED_SIZE;
        int X_pos_2 = (int) (bound.getX() + bound.getWidth() - 1) / SCALED_SIZE;
        int index_pos_1 = X_pos_1 + Y_pos * BombermanGame.WIDTH;
        int index_pos_2 = X_pos_2 + Y_pos * BombermanGame.WIDTH;
        return game.getStillObjects().get(index_pos_1).isSolid || game.getStillObjects().get(index_pos_2).isSolid;
    }

    public void animate() {
        if (_animate < 500)
            _animate++;
        else
            _animate = 0;
    }

    public abstract void createBound();

    public abstract void move();
}
