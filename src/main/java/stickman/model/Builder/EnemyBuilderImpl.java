package stickman.model.Builder;

import stickman.model.Factory.EnemyFactory;
import stickman.model.Factory.EntityFactory;
import stickman.model.entities.Enemy;
import stickman.model.entities.EntityType;

public class EnemyBuilderImpl extends EnemyBuilder {

    private Enemy enemy;
    double xpos;
    double ypos;
    int v;
    double range;
    String image1;
    String image2;
    EntityFactory entityFactory = new EnemyFactory();
    public EnemyBuilderImpl(double xpos,double ypos, int v
            , double range,String image1, String image2){
        enemy = (Enemy) entityFactory.getEntity();
        this.xpos =xpos;
        this.ypos = ypos;
        this.v = v;
        this.range =range;
        this.image1 = image1;
        this.image2 = image2;
    }

    @Override
    public void setVelocity() {
        enemy.setV(v);
    }

    @Override
    public void setRange() {
        enemy.setRange(range);
    }

    @Override
    public void setLeftRange() {
        enemy.setLeft_range(xpos);
    }

    @Override
    public void setRightRange() {
        enemy.setRight_range(xpos+range);
    }

    @Override
    public void setImagePath2() {
        enemy.setImagePath2(image2);
    }

    @Override
    public Enemy getEnemy() {
        return enemy;
    }

    @Override
    public void setXpos() {
        enemy.setXpos(xpos);
    }

    @Override
    public void setYpos() {
        enemy.setYpos(ypos);
    }

    @Override
    public void setImagePath() {
        enemy.setImagePath1(image1);
    }

    @Override
    public void setHeight() {
        enemy.setHeight(20);
    }

    @Override
    public void setWidth() {
        enemy.setWidth(20);
    }
}
