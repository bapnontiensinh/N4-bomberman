package uet.oop.bomberman.entities.bomb;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Animated.Brick;
import uet.oop.bomberman.entities.AnimatedEntity;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.solid.Grass;
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

        _animate = 0;
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

                kill();

            }
            if (afterExplode > 0) {
                --afterExplode;
            } else {

                remove();
                updateExplosion();

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


        if (game.getStillObjects().get(getUpIndex()) instanceof Brick && ((Brick) game.getStillObjects().get(getUpIndex())).removed) {
            game.getStillObjects().set(getUpIndex(), new Grass(getX(), getY(), Sprite.grass.getFxImage()));
            game.getStillObjects().get(getUpIndex()).update();
        }
        if (game.getStillObjects().get(getLeftIndex()) instanceof Brick && ((Brick) game.getStillObjects().get(getLeftIndex())).removed) {
            game.getStillObjects().set(getLeftIndex(), new Grass(getX(), getY(), Sprite.grass.getFxImage()));
            game.getStillObjects().get(getLeftIndex()).update();
        }
        if (game.getStillObjects().get(getRightIndex()) instanceof Brick && ((Brick) game.getStillObjects().get(getRightIndex())).removed) {
            game.getStillObjects().set(getRightIndex(), new Grass(getX(), getY(), Sprite.grass.getFxImage()));
            game.getStillObjects().get(getRightIndex()).update();
        }
        if (game.getStillObjects().get(getDownIndex()) instanceof Brick && ((Brick) game.getStillObjects().get(getDownIndex())).removed) {
            game.getStillObjects().set(getDownIndex(), new Grass(getX(), getY(), Sprite.grass.getFxImage()));
            game.getStillObjects().get(getDownIndex()).update();
        }
    }

    /**
     *
     */
    @Override
    public void render(GraphicsContext gc) {

        animate();
        if (exploded) {
            //img = Sprite.bomb_exploded2.getFxImage();
            this.img = Sprite.movingSprite(Sprite.bomb_exploded2, Sprite.bomb_exploded1,
                    Sprite.bomb_exploded, _animate, 160).getFxImage();
            for (int i = 0; i < explosions.length; ++i) {
                if (explosions[i] != null) {
                    if (!collisiontoUp()/* || game.getStillObjects().get(getUpIndex()).isActive()*/) {
                        explosions[0].update();
                        explosions[0].render(gc);
                    }
                    if (!collisiontoDown()/* || game.getStillObjects().get(getDownIndex()).isActive()*/) {
                        explosions[2].update();
                        explosions[2].render(gc);
                    }
                    if (!collisiontoLeft()/* || game.getStillObjects().get(getLeftIndex()).isActive()*/) {
                        explosions[1].update();
                        explosions[1].render(gc);
                    }
                    if (!collisiontoRight() /*|| game.getStillObjects().get(getRightIndex()).isActive()*/) {
                        explosions[3].update();
                        explosions[3].render(gc);
                    }
//                    if (game.getStillObjects().get(getUpIndex()).isActive()) {
//                        (game.getStillObjects().get(getUpIndex())).update();
//                        (game.getStillObjects().get(getUpIndex())).render(gc);
//
//                    }

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
