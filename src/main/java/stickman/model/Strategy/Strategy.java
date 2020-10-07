package stickman.model.Strategy;

import stickman.model.entities.Entity;

public interface Strategy {
    public boolean intersect(Entity a, Entity b);
}
