package uet.oop.bomberman.keyboard;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;

public class KeyBoard {
    public boolean up;
    public boolean down;
    public boolean left;
    public boolean right;
    public boolean space;

    public void addListener(Scene scene) {
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case UP:
                        up = true;
                        break;
                    case DOWN:
                        down = true;
                        break;
                    case LEFT:
                        left = true;
                        break;
                    case RIGHT:
                        right = true;
                        break;
                    case SPACE:
                        space = true;
                        break;
                }
            }
        });

        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case UP:
                        up = false;
                        break;
                    case DOWN:
                        down = false;
                        break;
                    case LEFT:
                        left = false;
                        break;
                    case RIGHT:
                        right = false;
                        break;
                    case SPACE:
                        space = false;
                }
            }
        });
    }
}
