package stickman.model.Factory;

import stickman.model.entities.Enemy;
import stickman.model.entities.Entity;

public class EnemyFactory implements EntityFactory {
    @Override
    public Entity getEntity() {
        return new Enemy();
    }
}
