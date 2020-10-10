package stickman.model.Factory;

import stickman.model.entities.Bullet;
import stickman.model.entities.Entity;

public class BulletFactory implements EntityFactory {
    @Override
    public Entity getEntity() {
        return new Bullet();
    }
}
