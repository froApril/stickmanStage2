package stickman.model.Builder;

import stickman.model.entities.EntityFactory;
import stickman.model.entities.EntityType;
import stickman.model.entities.Flag;

public class FlagBuilderImpl extends FlagBuilder {
    private Flag flag;
    private EntityFactory entityFactory =new EntityFactory();
    double xpos;
    double ypos;
    public FlagBuilderImpl(double xpos, double ypos){
        flag = (Flag) entityFactory.getEntity(EntityType.FLAG);
        this.xpos = xpos;
        this.ypos = ypos;
    }



    @Override
    public void setXpos() {
        flag.setXpos(xpos);
    }

    @Override
    public void setYpos() {
        flag.setYpos(ypos);
    }

    @Override
    public void setImagePath() {
        flag.setImagePath("/flag.png");
    }

    @Override
    public void setHeight() {
        flag.setHeight(15);
    }

    @Override
    public void setWidth() {
        flag.setWidth(10);
    }

    @Override
    public Flag getFlag() {
        return flag;
    }
}
