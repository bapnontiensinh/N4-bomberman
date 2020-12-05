package uet.oop.bomberman.keyboard;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;

import javafx.event.Event;
import javafx.scene.input.KeyEvent;

import java.awt.event.KeyListener;

import static java.awt.Event.DOWN;
import static java.awt.Event.UP;
import static java.awt.event.KeyEvent.*;

public class KeyBoard{
    public boolean up, down, left, right;
    //    private boolean key[];
//
//    public KeyManager() {
//        key = new boolean[256];
//    }
//
    public void addListener(Scene scene){
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case UP:    up = true; break;
                    case DOWN:  down = true; break;
                    case LEFT:  left  = true; break;
                    case RIGHT: right  = true; break;
                }
            }
        });

        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case UP:    up = false; break;
                    case DOWN:  down = false; break;
                    case LEFT:  left  = false; break;
                    case RIGHT: right  = false; break;
                }
            }
        });
    }
//    @Override
//    public void keyTyped(KeyEvent e) {
//        key[e.getKeyCode()]=true;
//    }
//
//    @Override
//    public void keyPressed(KeyEvent e) {
//        key[e.getKeyCode()]=false;
//    }
//
//    @Override
//    public void keyReleased(KeyEvent e) {
//
//    }

}
