package uet.oop.bomberman.entities.bomb;

import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.AnimatedEntity;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.graphics.Sprite.SCALED_SIZE;

public class Explosion extends AnimatedEntity {
    protected boolean next_display = true;
    protected boolean last;
    protected int direction;

    public Explosion(BombermanGame game, int x, int y, boolean last, int direction) {
        super(game, x, y, null);
        this.direction = direction;
        this.last = last;
        _animate = 0;
    }

    @Override
    public void update() {
        animate();
        move();
        kill();
    }

    @Override
    public void createBound() {
        super.createBound();
    }

    @Override
    public void move() {
        createBound();
        switch (direction) {
            case 0:
                try {
                    if (!game.getStillObjects().get(getIndex()).isSolid()) {
                        if (!last) {
                            this.img = Sprite.movingSprite(Sprite.explosion_vertical2, Sprite.explosion_vertical1,
                                    Sprite.explosion_vertical, _animate, 160).getFxImage();
                        } else {
                            this.img = Sprite.movingSprite(Sprite.explosion_vertical_top_last2, Sprite.explosion_vertical_top_last1,
                                    Sprite.explosion_vertical_top_last, _animate, 160).getFxImage();
                        }
                    } else {
                        next_display = false;
                    }
                } catch (Exception ignored) {

                }
                break;
            case 1:
                try {
                    if (!game.getStillObjects().get(getIndex()).isSolid()) {
                        if (!last) {
                            this.img = Sprite.movingSprite(Sprite.explosion_horizontal2, Sprite.explosion_horizontal1,
                                    Sprite.explosion_horizontal, _animate, 160).getFxImage();
                        } else {
                            this.img = Sprite.movingSprite(Sprite.explosion_horizontal_left_last2, Sprite.explosion_horizontal_left_last1,
                                    Sprite.explosion_horizontal_left_last, _animate, 160).getFxImage();
                        }
                    } else {
                        next_display = false;
                    }
                } catch (Exception ignored) {

                }
                break;
            case 2:
                try {
                    if (!game.getStillObjects().get(getIndex()).isSolid()) {
                        if (!last) {
                            this.img = Sprite.movingSprite(Sprite.explosion_vertical2, Sprite.explosion_vertical1,
                                    Sprite.explosion_vertical, _animate, 160).getFxImage();
                        } else {
                            this.img = Sprite.movingSprite(Sprite.explosion_vertical_down_last2, Sprite.explosion_vertical_down_last1,
                                    Sprite.explosion_vertical_down_last, _animate, 160).getFxImage();
                        }
                    } else {
                        next_display = false;
                    }
                } catch (Exception ignored) {
                }


                break;
            case 3:
                try {
                    if (!game.getStillObjects().get(getIndex()).isSolid()) {
                        if (!last) {
                            //img = Sprite.explosion_horizontal2.getFxImage();
                            this.img = Sprite.movingSprite(Sprite.explosion_horizontal2, Sprite.explosion_horizontal1,
                                    Sprite.explosion_horizontal, _animate, 160).getFxImage();
                        } else {
                            //img = Sprite.explosion_horizontal_right_last2.getFxImage();
                            this.img = Sprite.movingSprite(Sprite.explosion_horizontal_right_last2, Sprite.explosion_horizontal_right_last1,
                                    Sprite.explosion_horizontal_right_last, _animate, 160).getFxImage();
                        }
                    } else {
                        next_display = false;
                    }
                } catch (Exception ignored) {

                }

                break;
        }
    }

    @Override
    public void render(GraphicsContext gc) {
        super.render(gc);
        //   gc.fillRect(bound.getX(), bound.getY(), bound.getWidth(), bound.getHeight());
    }

    @Override
    public void remove() {

    }

    /**
     * kill
     */
    public void kill() {
        for (Entity entity : game.getEntities()) {
            if (this.bound.intersects(entity.bound.getX() + 1, entity.bound.getY() + 1, SCALED_SIZE - 2, SCALED_SIZE - 2))
                entity.setActive(false);
        }
        for (Entity entity : game.getStillObjects()) {
            if (this.bound.intersects(entity.bound.getX() + 1, entity.bound.getY() + 1, SCALED_SIZE - 2, SCALED_SIZE - 2))
                entity.setActive(false);
        }
    }
}
