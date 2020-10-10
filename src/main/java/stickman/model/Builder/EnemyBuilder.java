package stickman.model.Builder;

import stickman.model.entities.Enemy;

public abstract class EnemyBuilder extends EntityBuilder {

    public abstract void setVelocity();
    public abstract void setRange();
    public abstract void setLeftRange();
    public abstract void setRightRange();
    public abstract void setImagePath2();
    public abstract Enemy getEnemy();
}
