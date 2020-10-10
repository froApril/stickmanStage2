package stickman.model.Strategy;

import stickman.model.entities.Entity;

public class PlatformUnderIntersect implements Strategy {
    @Override
    public boolean intersect(Entity a, Entity b) {
        if(b.getYPos()+b.getHeight() == a.getYPos()
                &&(a.getXPos()>=b.getXPos()-10 && a.getXPos()<=b.getXPos()+a.getWidth()-10)){
            return true;
        }

        return false;
    }

    @Override
    public StrategyType getType() {
        return StrategyType.PLATFORM_BOTTOM;
    }
}
