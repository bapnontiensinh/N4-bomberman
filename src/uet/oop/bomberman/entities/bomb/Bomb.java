package uet.oop.bomberman.entities.bomb;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Animated.AnimatedEntity;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.BombermanGame.WIDTH;
import static uet.oop.bomberman.graphics.Sprite.SCALED_SIZE;
import static uet.oop.bomberman.graphics.Sprite.bomb;

public class Bomb extends AnimatedEntity {
    protected double timeToExplode = 120; // 2 seconds
    public int afterExplode = 50; // time to explosion disappear

    boolean exploded = false;
    public directionalExplosion[] explosions = null;
    private boolean removed = false;
    private boolean canBound= true;
    public Bomb(BombermanGame game, int x, int y, Image img) {
        super(game, x, y, img);
        createBound();
        canBound =true;
        isSolid=true;
    }

    public Bomb() {
        canBound=false;
    }

    @Override
    public void update() {

        if (timeToExplode > 0) {
            --timeToExplode;
            //System.out.print(timeToExplode);
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
    public void kill(){
        int x= this.x/SCALED_SIZE;
        int y=this.y/SCALED_SIZE;
        int index0= (y-1)* WIDTH+x;
        int index1= y*WIDTH+x-1;
        int index2= (y+1)*WIDTH+x;
        int index3=y*WIDTH+x+1;
        game.getStillObjects().get(index0).active=false;
        game.getStillObjects().get(index1).active=false;
        game.getStillObjects().get(index2).active=false;
        game.getStillObjects().get(index3).active=false;

    }
    public void explosion() {
        exploded = true;

        explosions = new directionalExplosion[4];

        for (int i = 0; i < 4; ++i) {
            explosions[i] = new directionalExplosion(game,x / SCALED_SIZE, y / SCALED_SIZE, i);
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
                    if (!collisiontoUp()){
                        explosions[0].render(gc);
                    }
                    if (!collisiontoDown()){
                        explosions[2].render(gc);
                    }
                    if (!collisiontoLeft()){
                        explosions[3].render(gc);
                        //WHY ??????????????????????????
                    }
                    if (!collisiontoRight()){
                        explosions[1].render(gc);
                    }
                    //explosions[i].render(gc);
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
        bound.setX(x);
        bound.setY(y);
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
