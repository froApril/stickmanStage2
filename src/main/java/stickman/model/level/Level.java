package stickman.model.level;

import stickman.model.entities.Entity;
import stickman.model.entities.Hero;

import java.util.List;

public interface Level {
    List<Entity> getEntities();
    double getHeight();
    double getWidth();

    double getFloorHeight();
    double getHeroX();
    double getHeroY();


    void shot(boolean left);

    boolean checkHeroMushroomCollision(Hero hero);

    void checkEnemyBulletCollision();

    boolean checkHeroEnemyCollision();

}
