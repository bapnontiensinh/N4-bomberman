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
//        SnapshotParameters params = new SnapshotParameters();
//        params.setFill(Color.TRANSPARENT); // tang hinh
//
//        ImageView iv = new ImageView(img); //tao hinh anh moi
//        Image base = iv.snapshot(params, null);

        //ve hinh anh o toa do x,y
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
