package stickman.model.Builder;

import stickman.model.Factory.EntityFactory;
import stickman.model.Factory.HeroFactory;
import stickman.model.entities.EntityType;
import stickman.model.entities.Hero;

public class HeroBuilderImpl extends HeroBuilder {
    private Hero hero;
    private EntityFactory entityFactory =new HeroFactory();
    double xpos;
    double ypos;
    String size;
    public HeroBuilderImpl(double xpos, double ypos, String size){
        hero = (Hero) entityFactory.getEntity();
        this.xpos = xpos;
        this.ypos = ypos;
        this.size = size;
    }

    @Override
    public void setSize() {

        hero.setSize(this.size);
        if(this.size.equals("normal")){
            hero.normalSize();
        }
        else{
            hero.largeSize();
        }
    }

    @Override
    public void setGroundLevel() {
        hero.setGroundLevel(ypos);
    }

    @Override
    public Hero getHero() {
        return hero;
    }

    @Override
    public void setXpos() {
        hero.setXpos(this.xpos);
    }

    @Override
    public void setYpos() {
        hero.setYpos(this.ypos);
    }

    @Override
    public void setImagePath() {
        hero.setImagePath("/ch_stand1.png");
    }

    @Override
    public void setHeight() {
        hero.setSize(size);
    }

    @Override
    public void setWidth() {
        hero.setSize(size);
    }
}
