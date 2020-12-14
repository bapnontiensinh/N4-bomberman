package uet.oop.bomberman.entities.Animated;

import com.sun.xml.internal.messaging.saaj.packaging.mime.internet.BMMimeMultipart;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Animated.AnimatedEntity;
import uet.oop.bomberman.entities.bomb.Bomb;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.graphics.Sprite.SCALED_SIZE;
import static uet.oop.bomberman.graphics.Sprite.bomb;

//Cài đặt moving và khắc phục lỗi bị khựng khi thay đổi speed.
public class Bomber extends AnimatedEntity {
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
            if (!collisiontoUp()) {
                this.img = Sprite.movingSprite(Sprite.player_up, Sprite.player_up_1,
                        Sprite.player_up_2, _animate, 20).getFxImage();
                y -= speed;

            }

        }
        if (game.getKeyBoard().down) {
            if (!collisiontoDown()) {
                this.img = Sprite.movingSprite(Sprite.player_down, Sprite.player_down_1,
                        Sprite.player_down_2, _animate, 20).getFxImage();

                y += speed;
            }
        }
        if (game.getKeyBoard().left) {
            if (!collisiontoLeft()) {
                this.img = Sprite.movingSprite(Sprite.player_left, Sprite.player_left_1,
                        Sprite.player_left_2, _animate, 20).getFxImage();
                x -= speed;
            }
        }
        if (game.getKeyBoard().right) {
            if (!collisiontoRight()) {
                this.img = Sprite.movingSprite(Sprite.player_right, Sprite.player_right_1,
                        Sprite.player_right_2, _animate, 10).getFxImage();
                x += speed;
            }
        }
        if (game.getKeyBoard().space){
            if (numberOfBomb<= MAX_BOMBS)
            createBomb();
        }
    }
    private boolean show = false;
    private int numberOfBomb=0;
    private final int MAX_BOMBS=1;
    private Bomb bomb =new Bomb();
    private void createBomb() {
       // show =true;
        numberOfBomb++;
        bomb = new Bomb((int)bound.getX()/SCALED_SIZE,(int)bound.getY()/SCALED_SIZE,Sprite.bomb.getFxImage());
     //   bomb = new Bomb(1,3,Sprite.bomb.getFxImage());

      //  System.out.println("Create BOmb");
    }


    @Override
    public void update() {
        animate();
        move();
        kill();
        bomb.update();
       if (bomb.exploded)
        numberOfBomb--;
    }

    private void kill() {

    }

    @Override
    public void render(GraphicsContext gc) {
        gc.drawImage(img, x, y);
       // if (show == true){
            this.bomb.render(gc);

        //    show=false ;
        //}

       // gc.fillRect(bound.getX(), bound.getY(), bound.getWidth(), bound.getHeight());
    }

    @Override
    public void createBound() {
//        bound.setWidth(DEFAULT_SIZE);
//        bound.setHeight(SCALED_SIZE * 3 / 4);
//        bound.setX(x + SCALED_SIZE / 2 - bound.getWidth() / 2 - 6);
//        bound.setY(y + SCALED_SIZE * 1 / 8);

        bound.setWidth(SCALED_SIZE-6);
        bound.setHeight(SCALED_SIZE-6);
        bound.setX(x+6);
        bound.setY(y+6);

//        bound.setWidth(SCALED_SIZE);
//        bound.setHeight(SCALED_SIZE);
//        bound.setX(x);
//        bound.setY(y);
    }
}
