package stickman.model.Builder;

import stickman.model.Factory.EntityFactory;
import stickman.model.Factory.MushroomFactory;
import stickman.model.entities.EntityType;
import stickman.model.entities.Mushroom;

public class MushroomsBuilderImpl extends MushroomsBuilder {
    private Mushroom mushroom;
    double xpos;
    double ypos;
    EntityFactory entityFactory = new MushroomFactory();
    public MushroomsBuilderImpl(double xpos, double ypos){
        mushroom = (Mushroom) entityFactory.getEntity();
        this.ypos = ypos;
        this.xpos = xpos;
    }
    @Override
    public void setImagePath() {
        mushroom.setImagePath("/mushroom.png");
    }

    @Override
    public void setHeight() {
        mushroom.setHeight(10);

    }

    @Override
    public void setWidth() {
        mushroom.setWidth(10);
    }

    @Override
    public Mushroom getMushroom() {
        return mushroom;
    }

    @Override
    public void setXpos() {
        mushroom.setXpos(xpos);
    }

    @Override
    public void setYpos() {
        mushroom.setYpos(ypos);
    }
}
