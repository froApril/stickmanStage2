package stickman.model.Factory;

import stickman.model.entities.Entity;
import stickman.model.entities.Platform;

public class PlatformFactory implements EntityFactory {
    @Override
    public Entity getEntity() {
        return new Platform();
    }
}
