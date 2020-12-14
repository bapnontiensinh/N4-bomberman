package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import uet.oop.bomberman.entities.Animated.Bomber;
import uet.oop.bomberman.entities.Brick;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Grass;
import uet.oop.bomberman.entities.bomb.Bomb;
import uet.oop.bomberman.graphics.Camera;
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
    public final int MAX_BOMBS = 1;
    public int bombExisited = 0;
    public Bomb bomb = new Bomb();
    public List<Bomb> bombList = new ArrayList<>();


    /**
     * Player
     */
    public Bomber player;

    private List<Entity> entities = new ArrayList<>();
    private List<Entity> stillObjects = new ArrayList<>();

    Camera gameCam;
    private GraphicsContext gc;
    private Canvas canvas;

    /**
     * Keyboard
     */
    private KeyBoard keyBoard = new KeyBoard();

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

        gameCam = new Camera(0, 0);

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

        createMap();
    }

    /**
     * Create Map
     */
    public void createMap() {
        loadLevel setLevel = new loadLevel(this);
        try {
            stillObjects = setLevel.updateLevel(1);
            entities = setLevel.getEntities();
            player = setLevel.getPlayer();
        } catch (IOException e) {
            System.out.println("Cannot open file");
        }
    }

    public void createBomb() {
        bombExisited++;
        bomb = new Bomb(this, (int) player.bound.getX() / SCALED_SIZE, (int) player.getY() / SCALED_SIZE, Sprite.bomb.getFxImage());
        entities.add(bomb);
    }

    public void update() {

        for (int i = 0; i < entities.size(); i++) {
            Entity entity = entities.get(i);
            entity.update();
            if (entity instanceof Bomb) {
                if (((Bomb) entity).isRemoved()) {
                    entities.remove(entity);
                }
            }
        }
        for (int i = 0; i < stillObjects.size(); i++) {
            Entity entity = stillObjects.get(i);
            entity.update();
            if (entity instanceof Brick) {
                if (!((Brick) entity).isActive()) {
                    stillObjects.set(i, new Grass(entity.getX() / SCALED_SIZE, entity.getY() / SCALED_SIZE, Sprite.grass.getFxImage()));
                }
            }
        }

//        for (Entity entity : entities) {
//            entity.update();
//        }
//         if (player.bomb.isRemoved()) entities.remove(player.bomb);
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        stillObjects.forEach(g -> g.render(gc));
        entities.forEach(g -> g.render(gc));
    }

    public List<Entity> getEntities() {
        return entities;
    }

    public List<Entity> getStillObjects() {
        return stillObjects;
    }

    public KeyBoard getKeyBoard() {
        return keyBoard;
    }

    public Camera getGameCam() {
        return gameCam;
    }
}