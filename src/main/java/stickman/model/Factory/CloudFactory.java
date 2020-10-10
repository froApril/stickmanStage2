package stickman.model.Factory;

import stickman.model.entities.Cloud;
import stickman.model.entities.Entity;

public class CloudFactory implements EntityFactory {
    @Override
    public Entity getEntity() {
        return new Cloud();
    }
}
