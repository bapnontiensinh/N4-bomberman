package uet.oop.bomberman.level;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.graphics.Sprite;

import javax.swing.*;

import static uet.oop.bomberman.BombermanGame.HEIGHT;
import static uet.oop.bomberman.BombermanGame.WIDTH;


public class loadLevel {
    private List<Entity> stillObjects = new ArrayList<>();

    public List<Entity> updateLevel(int level) throws IOException {
        FileReader file = new FileReader("res/levels/Level" + level + ".txt");
        //String filename = "Level" + level + ".txt";

        BufferedReader in = new BufferedReader(file);

        char c;
        String s;

        for (int i = 0; i < HEIGHT; ++i) {
            s = in.readLine();
            for (int j = 0; j < WIDTH; ++j) {
                c = s.charAt(j);
                addEntity(c, j, i);
            }
        }

        return stillObjects;
    }

    public void addEntity(char c, int x, int y) {
        switch(c) {
            case '#':
                stillObjects.add(new Wall(x, y, Sprite.wall.getFxImage()));
                break;
            case '*':
                stillObjects.add(new Brick(x, y, Sprite.brick.getFxImage()));
                break;
            /*case 'p':
                stillObjects.add(new Bomber(x, y, Sprite.player_down.getFxImage()));
                break;*/
            case '1':
                stillObjects.add(new Enemy(x, y, Sprite.balloom_left1.getFxImage()));
                break;
            default:
                stillObjects.add(new Grass(x, y, Sprite.grass.getFxImage()));
        }
    }
}
