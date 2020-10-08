package stickman.model.level;

import stickman.model.Strategy.GeneralIntersect;
import stickman.model.entities.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class LevelImpl implements Level {
    private Entity hero;
    private List<Entity> clouds;
    private List<Entity> platforms;
    private Flag flag;
    private List<Entity> mushrooms;
    private List<Entity> bullets;

    public LevelImpl(Entity hero, List<Entity> clouds, List<Entity> platforms
            , Flag flag, List<Entity> mushrooms){
        this.hero = hero;
        this.clouds = clouds;
        this.platforms = platforms;
        this.flag = flag;
        this.mushrooms = mushrooms;
        bullets = new ArrayList<>();
    }

    @Override
    public List<Entity> getEntities() {
        List<Entity> res = new ArrayList<>();
        res.add(hero);
        res.addAll(clouds);
        res.addAll(platforms);
        res.add(flag);
        res.addAll(mushrooms);

        List<Entity> removeList = new ArrayList<>();
        for(Entity entity:bullets){
            if(entity.getDisplay()){
                res.add(entity);
            }
            else{
                removeList.add(entity);
            }
        }
        removeBullet(removeList);
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
    public boolean checkHeroMushroomCollision(Hero hero) {
        for(Entity mushroom:mushrooms){
            if(mushroom.collision(hero,new GeneralIntersect())){
                Hero.getStrength();
                return true;
            }
        }
        return false;
    }

    @Override
    public double getHeroY(){
        return hero.getYPos();
    }

    public void shot(int direction){
        //direction = 0 left
        //direction = 1 right
        if(direction ==0){
            int v = -1;
            Bullet bullet = new Bullet();
            bullet.shot(hero.getXPos()-1,hero.getYPos()+8,v);
            bullets.add(bullet);
        }
        else{
            int v = 1;
            Bullet bullet = new Bullet();
            bullet.shot(hero.getXPos()+1,hero.getYPos()+8,v);
            bullets.add(bullet);
        }

    }
    private void removeBullet(List<Entity> list){
        for(Entity entity:list){
            bullets.remove(entity);
        }
    }

}
