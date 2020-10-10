package stickman.model.Factory;

import stickman.model.entities.Entity;
import stickman.model.entities.Hero;

public class HeroFactory implements EntityFactory {
    @Override
    public Entity getEntity() {
        return new Hero();
    }
}
