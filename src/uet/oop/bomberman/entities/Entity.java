package uet.oop.bomberman.entities;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

public abstract class Entity {
    // add
    public Rectangle bound;
    public boolean isSolid;
    protected int x;
    protected int y;
    protected Image img;
    public boolean active=false;

    //add
    public Entity() {

    }

    public Entity(int xUnit, int yUnit, Image img) {
        this.x = xUnit * Sprite.SCALED_SIZE;
        this.y = yUnit * Sprite.SCALED_SIZE;
        this.img = img;
        bound = new Rectangle();
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(img, x, y);

    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public abstract void update();

}
