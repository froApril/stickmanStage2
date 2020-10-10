package stickman.model.Factory;

import stickman.model.entities.Entity;
import stickman.model.entities.Flag;

public class FlagFactory implements EntityFactory {
    @Override
    public Entity getEntity() {
        return new Flag();
    }
}
