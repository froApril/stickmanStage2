package stickman.model.Factory;

import stickman.model.entities.Entity;
import stickman.model.entities.Mushroom;

public class MushroomFactory implements EntityFactory {
    @Override
    public Entity getEntity() {
        return new Mushroom();
    }
}
