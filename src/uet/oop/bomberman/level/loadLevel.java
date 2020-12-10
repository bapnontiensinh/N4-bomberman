package uet.oop.bomberman.level;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.graphics.Sprite;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static uet.oop.bomberman.BombermanGame.HEIGHT;
import static uet.oop.bomberman.BombermanGame.WIDTH;


public class loadLevel {
    private List<Entity> stillObjects = new ArrayList<>();
    private List<Entity> entities = new ArrayList<>();
    private BombermanGame game;
    private Bomber player;
    public loadLevel(BombermanGame game) {
        this.game = game;
    }

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
        switch (c) {
            case '#':
                stillObjects.add(new Wall(x, y, Sprite.wall.getFxImage()));
                break;
            case '*':
                stillObjects.add(new Brick(x, y, Sprite.brick.getFxImage()));
                break;
            case 'p':
                stillObjects.add(new Grass(x, y, Sprite.grass.getFxImage()));
                player =new Bomber(game, x, y, Sprite.player_down.getFxImage());
                entities.add(player);
                break;
            case '1':
                stillObjects.add(new Grass(x, y, Sprite.grass.getFxImage()));
                entities.add(new Enemy(game, x, y, Sprite.balloom_left1.getFxImage()));

                break;
            default:
                stillObjects.add(new Grass(x, y, Sprite.grass.getFxImage()));
        }
    }

    public Bomber getPlayer() {
        return player;
    }

    public List<Entity> getEntities() {
        return entities;
    }

    public List<Entity> getStillObjects() {
        return stillObjects;
    }
}
