package uet.oop.bomberman.entities;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import uet.oop.bomberman.keyboard.Keyboard;

public class Bomber extends Entity {
    public Keyboard keyPressed = new Keyboard();
    private int bombs = 0;

    public Bomber(int x, int y, Image img) {
        super( x, y, img);
    }

    public void calcMove() {
        int x = 0;
        int y = 0;

        if (keyPressed.down) --y;
        if (keyPressed.left) --x;
        if (keyPressed.right) ++x;
        if (keyPressed.up) ++y;

        if ((x != 0) || (y != 0)) {
            move(x, y);
        }
    }

    public void move(int _x, int _y) {
        this.x += _x;
        this.y += _y;
    }

    @Override
    public void update() {
        calcMove();
    }
}
