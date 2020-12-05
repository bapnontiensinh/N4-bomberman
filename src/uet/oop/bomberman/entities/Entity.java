package uet.oop.bomberman.entities;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import uet.oop.bomberman.graphics.Sprite;

public abstract class Entity {
    protected int x;
    protected int y;
    protected Image img;

//    public Entity( int x, int y, Image img) {
//        this.x = x;
//        this.y = y;
//        this.img = img;
//    }
public Entity( int xUnit, int yUnit, Image img) {
    this.x = xUnit * Sprite.SCALED_SIZE;
    this.y = yUnit * Sprite.SCALED_SIZE;
    this.img = img;
}
    public void render(GraphicsContext gc) {
//        SnapshotParameters params = new SnapshotParameters();
//        params.setFill(Color.TRANSPARENT); // tang hinh
//
//        ImageView iv = new ImageView(img); //tao hinh anh moi
//        Image base = iv.snapshot(params, null);

        //ve hinh anh o toa do x,y
        gc.drawImage(img, x , y );

    }
    public abstract void update();
}
