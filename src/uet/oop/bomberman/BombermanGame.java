package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import uet.oop.bomberman.entities.Animated.Bomber;
import uet.oop.bomberman.entities.Animated.Brick;
import uet.oop.bomberman.entities.Animated.Enemy;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Powerup.Powerup;
import uet.oop.bomberman.entities.bomb.Bomb;
import uet.oop.bomberman.entities.solid.Grass;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.keyboard.KeyBoard;
import uet.oop.bomberman.level.loadLevel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static uet.oop.bomberman.graphics.Sprite.SCALED_SIZE;

public class BombermanGame extends Application {
    public static final int WIDTH = 20;
    public static final int HEIGHT = 15;
    /**
     * Bomb
     */
    public int bombExisited = 0;
    public Bomb bomb = new Bomb();
    public int maxBomb = 1;
    private int bomblength = 1;
    /**
     * Player
     */
    public Bomber player;
    public int lives = 3;

    private int currentLevel = 1;

    private List<Entity> entities = new ArrayList<>();
    private List<Entity> stillObjects = new ArrayList<>();
    private GraphicsContext gc;
    private Canvas canvas;

    /**
     * Keyboard
     */
    private final KeyBoard keyBoard = new KeyBoard();

    private int numEnemy = 0;

    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    /**
     * Start Method
     */
    @Override
    public void start(Stage stage) {
        canvas = new Canvas(SCALED_SIZE * WIDTH, SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();
        Group root = new Group();
        root.getChildren().add(canvas);
        Scene scene = new Scene(root);

        keyBoard.addListener(scene);

        stage.setScene(scene);
        stage.show();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                render();
                update();
            }
        };
        timer.start();

        createMap(currentLevel);

    }

    /**
     * Create Map
     */
    public void createMap(int level) {
        loadLevel setLevel = new loadLevel(this);
        try {
            stillObjects = setLevel.updateLevel(level);
            entities = setLevel.getEntities();
            player = setLevel.getPlayer();
            bombExisited = 0;
            maxBomb = 1;
            bomblength = 1;
        } catch (IOException e) {
            System.out.println("Cannot open file");
        }
    }

    /**
     * create bomb method
     */
    public void createBomb() {
        if (bombExisited < maxBomb) {
            bombExisited++;
            bomb = new Bomb(this, player.getX_center_unit(), player.getY_center_unit(),
                    Sprite.bomb.getFxImage(), bomblength);
            entities.add(bomb);
        }
    }

    /**
     * update
     */
    public void update() {
        updateEntities();
        updateStillObject();
    }

    /**
     *
     */
    private void updateEntities() {
        for (int i = 0; i < entities.size(); i++) {
            Entity entity = entities.get(i);
            entity.update();

            if (entity instanceof Bomb) {
                if (((Bomb) entity).isRemoved()) {
                    entities.remove(entity);
                    if (maxBomb > 1) {
                        bombExisited--;
                        if (bombExisited < -10) bombExisited = 0;
                    }
                }
            }

            if (entity instanceof Powerup) {
                if (((Powerup) entity).isRemoved()) {
                    entities.remove(entity);
                }
            }

            if (entity instanceof Enemy) {
                if (!entity.isActive())
                    entities.remove(entity);
            }
        }
    }

    public void updateStillObject() {
        for (int i = 0; i < stillObjects.size(); i++) {
            Entity entity = stillObjects.get(i);
            entity.update();

            if (entity instanceof Brick) {
                if (!entity.isActive()) {
                    stillObjects.set(i, new Grass(entity.getX(), entity.getY(), Sprite.grass.getFxImage()));

                    if (((Brick) entity).getContainPowerup() != null) {
                        entities.add(((Brick) entity).getContainPowerup());
                    }
                }
            }
        }
    }

    /**
     * render
     */
    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        stillObjects.forEach(g -> g.render(gc));
        entities.forEach(g -> g.render(gc));
    }

    /**
     * getter & setter
     */
    public List<Entity> getEntities() {
        return entities;
    }

    public List<Entity> getStillObjects() {
        return stillObjects;
    }

    public KeyBoard getKeyBoard() {
        return keyBoard;
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
    }

    public int getBomblength() {
        return bomblength;
    }

    public void setBomblength(int bomblength) {
        this.bomblength = bomblength;
    }

    public int getNumEnemy() {
        return numEnemy;
    }

    public void setNumEnemy(int numEnemy) {
        this.numEnemy = numEnemy;
    }
}