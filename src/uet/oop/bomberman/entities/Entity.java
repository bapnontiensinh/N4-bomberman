package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import uet.oop.bomberman.graphics.Sprite;

public abstract class Entity {
    protected int x;
    protected int y;
    protected int width,height;
    protected Image img;
    // add
    protected Rectangle bound;
    public boolean isSolid;

    //add

    //    public Entity( int x, int y, Image img) {
//        this.x = x;
//        this.y = y;
//        this.img = img;
//    }
    public Entity(int xUnit, int yUnit, Image img) {
        this.x = xUnit * Sprite.SCALED_SIZE;
        this.y = yUnit * Sprite.SCALED_SIZE;
        this.img = img;
        bound=new Rectangle();
        createBound(bound,0,0,width,height);
    }
    private void createBound(Rectangle rectangle,int x, int y,int width,int height ){
        rectangle.setX(x);
        rectangle.setY(y);
        rectangle.setWidth(width);
        rectangle.setHeight(height);
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

    public abstract void update();
}
