package uet.oop.bomberman.entities.Animated;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.bomb.Bomb;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.graphics.Sprite.SCALED_SIZE;

//Cài đặt moving và khắc phục lỗi bị khựng khi thay đổi speed.

public class Bomber extends AnimatedEntity {
    private final int MAX_BOMBS = 1;
    public boolean alive = true;
    private boolean show = false;
    private int bombExisited = 0;
    private Bomb bomb = new Bomb();

    public Bomber(BombermanGame game, int x, int y, Image img) {
        super(game, x, y, img);
        speed = 2;
        //  show =false;
    }

    // Can mo rong cho 1 bound
    @Override
    public void move() {
        createBound();
        if (game.getKeyBoard().up) {
            this.img = Sprite.movingSprite(Sprite.player_up, Sprite.player_up_1,
                    Sprite.player_up_2, _animate, 20).getFxImage();
            if (!collisiontoUp()) {

                y -= speed;

            }

        }
        if (game.getKeyBoard().down) {
            this.img = Sprite.movingSprite(Sprite.player_down, Sprite.player_down_1,
                    Sprite.player_down_2, _animate, 20).getFxImage();
            if (!collisiontoDown()) {
                y += speed;
            }
        }
        if (game.getKeyBoard().left) {
            this.img = Sprite.movingSprite(Sprite.player_left, Sprite.player_left_1,
                    Sprite.player_left_2, _animate, 20).getFxImage();
            if (!collisiontoLeft()) {
                x -= speed;
            }
        }
        if (game.getKeyBoard().right) {
            this.img = Sprite.movingSprite(Sprite.player_right, Sprite.player_right_1,
                    Sprite.player_right_2, _animate, 20).getFxImage();
            if (!collisiontoRight()) {
                x += speed;
            }
        }
        if (game.getKeyBoard().space) {
            if (bombExisited < MAX_BOMBS)
                createBomb();
        }
    }

    private void createBomb() {
        // show =true;
        bombExisited++;
        bomb = new Bomb(game,(int) bound.getX() / SCALED_SIZE, (int) bound.getY() / SCALED_SIZE, Sprite.bomb.getFxImage());
    }

    @Override
    public void update() {
        animate();
        move();
        kill();
        if (bombExisited!=0){
            bomb.update();
            if (bomb.finished)
                bombExisited--;
        }

    }

    private void kill() {
        if (!alive) {
            this.img = Sprite.movingSprite(Sprite.player_dead1, Sprite.player_dead2,
                    Sprite.player_dead3, _animate, 20).getFxImage();
            game = new BombermanGame();
            System.out.println("Game Over? Restart? Y/N");
        }
    }

    @Override
    public void render(GraphicsContext gc) {
        gc.drawImage(img, x, y);
        this.bomb.render(gc);
        // gc.fillRect(bound.getX(), bound.getY(), bound.getWidth(), bound.getHeight());
    }

    @Override
    public void createBound() {
        bound.setWidth(SCALED_SIZE - 6);
        bound.setHeight(SCALED_SIZE - 6);
        bound.setX(x + 6);
        bound.setY(y + 6);
    }
}
