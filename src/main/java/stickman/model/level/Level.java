package stickman.model.level;

import stickman.model.entities.Entity;

import java.util.List;

public interface Level {
    List<Entity> getEntities();
    double getHeight();
    double getWidth();

    void tick();

    double getFloorHeight();
    double getHeroX();
    double getHeroY();

    boolean jump();
    boolean moveLeft();
    boolean moveRight();
    boolean stopMoving();
}
