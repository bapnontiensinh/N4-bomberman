package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

import static uet.oop.bomberman.BombermanGame.WIDTH;
import static uet.oop.bomberman.graphics.Sprite.SCALED_SIZE;

public abstract class Entity {
    public Rectangle bound;
    public Image img;
    protected int x;
    protected int y;
    protected int x_real;
    protected int y_real;
    protected boolean solid;
    protected boolean active = false;


    public Entity() {

    }

    /**
     * Javadoc
     */
    public Entity(int x, int y, Image img) {
//        this.x = xUnit * Sprite.SCALED_SIZE;
//        this.y = yUnit * Sprite.SCALED_SIZE;
        this.x = x;
        this.y = y;
        this.img = img;
        getCoodinate();
        bound = new Rectangle();
    }

    /**
     * Javadoc
     */
    public void getCoodinate() {
        x_real = x * SCALED_SIZE;
        y_real = y * SCALED_SIZE;
    }

    public abstract void update();

    public void render(GraphicsContext gc) {
        gc.drawImage(img, x_real, y_real);
    }

    /**
     * getter&setter
     */
    public int getX_real() {
        return x_real;
    }

    public int getY_real() {
        return y_real;
    }

    public int getUpIndex() {
        return getX() + (getY() - 1) * WIDTH;
    }

    public int getDownIndex() {
        return getX() + (getY() + 1) * WIDTH;
    }

    public int getLeftIndex() {
        return getX() - 1 + getY() * WIDTH;
    }

    public int getRightIndex() {
        return getX() + 1 + getY() * WIDTH;
    }

    public int getIndex() {
        return getX_center_unit() + getY_center_unit() * WIDTH;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public boolean isSolid() {
        return solid;
    }

    public void setSolid(boolean solid) {
        this.solid = solid;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getX_center() {
        return x_real + SCALED_SIZE / 2;
    }

    public int getX_center_unit() {
        return getX_center() / SCALED_SIZE;
    }

    public int getY_center() {
        return y_real + SCALED_SIZE / 2;
    }

    public int getY_center_unit() {
        return getY_center() / SCALED_SIZE;
    }
}
