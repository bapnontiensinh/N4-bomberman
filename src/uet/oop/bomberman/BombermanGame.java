package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import uet.oop.bomberman.entities.Animated.Bomber;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.bomb.Bomb;
import uet.oop.bomberman.graphics.Camera;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.keyboard.KeyBoard;
import uet.oop.bomberman.level.loadLevel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BombermanGame extends Application {

    public static final int WIDTH = 20;
    public static final int HEIGHT = 15;

    private List<Entity> entities = new ArrayList<>();
    private List<Entity> stillObjects = new ArrayList<>();

    public Bomber player;


    private Bomb a_bomb = null;

    private GraphicsContext gc;
    private Canvas canvas;

    Camera gameCam;

    /**
     *
     */
    private boolean running;

    /**
     * Keyboard
     */
    private KeyBoard keyBoard = new KeyBoard();

    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) {
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();
        Group root = new Group();
        root.getChildren().add(canvas);
        Scene scene = new Scene(root);

        //
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

        a_bomb = new Bomb(this, 3, 3, Sprite.bomb.getFxImage());
        entities.add(a_bomb);

        //start((Bomber) bomberman);
    }

    public void createMap() {
        loadLevel setLevel = new loadLevel(this);
        try {
            stillObjects = setLevel.updateLevel(1);
            entities= setLevel.getEntities();
            player=setLevel.getPlayer();
        } catch (IOException e) {
            System.out.print("Cannot open file");
        }
    }

    public void update() {
        entities.forEach(Entity::update);
        if (a_bomb.isRemoved()) entities.remove(a_bomb);
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        stillObjects.forEach(g -> g.render(gc));
        entities.forEach(g -> g.render(gc));
    }

    public void start(Bomber bomber) {
        running = true;
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

    public static int getHEIGHT() {
        return HEIGHT;
    }

    public static int getWIDTH() {
        return WIDTH;
    }
}