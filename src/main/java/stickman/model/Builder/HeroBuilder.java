package stickman.model.Builder;

import stickman.model.entities.Hero;

public abstract class HeroBuilder extends EntityBuilder {
    public abstract void setSize();

    public abstract void setGroundLevel();

    public abstract Hero getHero();
}
