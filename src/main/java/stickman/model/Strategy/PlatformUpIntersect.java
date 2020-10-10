package stickman.model.Strategy;

import stickman.model.entities.Entity;

public class PlatformUpIntersect implements Strategy {
    @Override
    public boolean intersect(Entity a, Entity b) {
        //a is hero
        double current_height =a.getHeight()+a.getYPos();
        if(b.getYPos()==current_height && a.getXPos()>=b.getXPos()-10
                && a.getXPos()<=b.getXPos()+a.getWidth()-10){
            return true;
        }
        return false;
    }

    @Override
    public StrategyType getType() {
        return StrategyType.PLATFORM_UP;
    }
}