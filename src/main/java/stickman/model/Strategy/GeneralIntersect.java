package stickman.model.Strategy;

import stickman.model.entities.Entity;

public class GeneralIntersect implements Strategy {
    @Override
    public boolean intersect(Entity a, Entity b) {
        return (a.getXPos() < (b.getXPos() + b.getWidth())) &&
                ((a.getXPos() + a.getWidth()) > b.getXPos()) &&
                (a.getYPos() < (b.getYPos() + b.getHeight())) &&
                ((a.getYPos() + a.getHeight()) > b.getYPos());
    }
}
