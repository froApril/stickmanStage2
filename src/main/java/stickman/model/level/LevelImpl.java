package stickman.model.level;

import stickman.model.entities.Cloud;
import stickman.model.entities.Entity;
import stickman.model.entities.Hero;

import java.util.ArrayList;
import java.util.List;

public class LevelImpl implements Level {
    private Entity hero;
    private List<Entity> clouds;
    private List<Entity> platforms;

    public LevelImpl(Entity hero, List<Entity> clouds,List<Entity> platforms){
        this.hero = hero;
        this.clouds = clouds;
        this.platforms = platforms;
    }

    @Override
    public List<Entity> getEntities() {
        List<Entity> res = new ArrayList<>();
        res.add(hero);
        res.addAll(clouds);
        res.addAll(platforms);
        return res;
    }

    @Override
    public double getHeight() {
        return 0;
    }

    @Override
    public double getWidth() {
        return 0;
    }

    @Override
    public void tick() {

    }

    @Override
    public double getFloorHeight() {
        return 300;
    }

    @Override
    public double getHeroX() {
        return hero.getXPos();
    }

    @Override
    public boolean jump() {
        return false;
    }

    @Override
    public boolean moveLeft() {
        return false;
    }

    @Override
    public boolean moveRight() {
        return false;
    }

    @Override
    public boolean stopMoving() {
        return false;
    }

    @Override
    public double getHeroY(){
        return hero.getYPos();
    }
}