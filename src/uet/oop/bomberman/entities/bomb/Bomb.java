package uet.oop.bomberman.entities.bomb;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Animated.AnimatedEntity;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.graphics.Sprite.SCALED_SIZE;

public class Bomb extends AnimatedEntity {
    public int afterExplode = 50; // time to explosion disappear
    public directionalExplosion[] explosions = null;
    protected double timeToExplode = 120; // 2 seconds
    private boolean exploded = false;
    private boolean removed = false;
    private boolean canBound;

    public Bomb(BombermanGame game, int x, int y, Image img) {
        super(game, x, y, img);
        createBound();
        canBound = true;
        solid = true;
    }

    public Bomb() {
        canBound = false;
    }

    @Override
    public void update() {
        animate();
        if (timeToExplode > 0) {
            --timeToExplode;
            this.img = Sprite.movingSprite(Sprite.bomb, Sprite.bomb_1,
                    Sprite.bomb_2, _animate, 40).getFxImage();
        } else {
            if (!exploded) {
                explosion();
            } else {
                updateExplosion();
            }
            if (afterExplode > 0) {
                --afterExplode;
            } else {
                remove();
                kill();
            }
        }
    }

    public void kill() {
        for (int i = 0; i < 4; i++) {
            int index = caculate(i, x, y);
            game.getStillObjects().get(index).setActive(false);
        }
//        for (Entity entity :
//                game.getEntities()) {
//            if (entity.getIndex()== game.bomb.getUpIndex()
//                    || entity.getIndex() == game.bomb.getDownIndex()
//                    || entity.getIndex() == game.bomb.getLeftIndex()
//                    || entity.getIndex() == game.bomb.getRightIndex()) {
//                entity.setActive(false);
//            }
//        }
        for (Entity entity :
                game.getEntities()) {
            if (entity.bound.intersects(game.bomb.bound.getX(), game.bomb.bound.getY() - SCALED_SIZE, SCALED_SIZE, SCALED_SIZE)
                    || entity.bound.intersects(game.bomb.bound.getX() + SCALED_SIZE, game.bomb.bound.getY(), SCALED_SIZE, SCALED_SIZE)
                    || entity.bound.intersects(game.bomb.bound.getX() - SCALED_SIZE, game.bomb.bound.getY(), SCALED_SIZE, SCALED_SIZE)
                    || entity.bound.intersects(game.bomb.bound.getX(), game.bomb.bound.getY() + SCALED_SIZE, SCALED_SIZE, SCALED_SIZE))
                entity.setActive(false);
        }
    }
//        if (bound.intersects(game.player.getX_real(),game.player.getY_real(),SCALED_SIZE,SCALED_SIZE)){
//            game.player.setActive(false);
//            game.player.die();
//        }


    public void explosion() {
        exploded = true;
        explosions = new directionalExplosion[4];
        for (int i = 0; i < 4; ++i) {
            explosions[i] = new directionalExplosion(game, x, y, i);
        }
    }

    public void updateExplosion() {

    }


    /**
     *
     */
    @Override
    public void render(GraphicsContext gc) {
        if (exploded) {
            img = Sprite.bomb_exploded2.getFxImage();
            for (int i = 0; i < explosions.length; ++i) {
                if (explosions[i] != null) {
                    if (!collisiontoUp() || game.getStillObjects().get(getUpIndex()).isActive()) {
                        explosions[0].render(gc);
                    }
                    if (!collisiontoDown() || game.getStillObjects().get(getDownIndex()).isActive()) {
                        explosions[2].render(gc);
                    }
                    if (!collisiontoLeft() || game.getStillObjects().get(getLeftIndex()).isActive()) {
                        explosions[1].render(gc);
                    }
                    if (!collisiontoRight() || game.getStillObjects().get(getRightIndex()).isActive()) {
                        explosions[3].render(gc);
                    }
                }
            }
        }
        super.render(gc);
        // if (canBound)
        // gc.fillRect(bound.getX(), bound.getY(), bound.getWidth(), bound.getHeight());
    }

    public boolean isCanBound() {
        return canBound;
    }

    @Override
    public void remove() {
        removed = true;
        // game.getEntities().remove(this);
        //game.toRemove.add(this);
        //game.bombList.remove(this);
        game.bombExisited--;
        //  System.out.println("remove");
    }


    @Override
    public void createBound() {
        bound.setWidth(SCALED_SIZE);
        bound.setHeight(SCALED_SIZE);
        bound.setX(x_real);
        bound.setY(y_real);
    }

    @Override
    public void move() {

    }

    public boolean isRemoved() {
        return removed;
    }

    public directionalExplosion[] getExplosions() {
        return explosions;
    }
}
