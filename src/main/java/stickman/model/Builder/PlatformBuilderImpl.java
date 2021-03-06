package stickman.model.Builder;

import stickman.model.Factory.EntityFactory;
import stickman.model.Factory.PlatformFactory;
import stickman.model.entities.Platform;

public class PlatformBuilderImpl extends PlatformBuilder {
    private Platform platform;
    private EntityFactory entityFactory =new PlatformFactory();
    double xpos;
    double ypos;
    public PlatformBuilderImpl(double xpos, double ypos){
        platform= (Platform) entityFactory.getEntity();
        this.xpos = xpos;
        this.ypos = ypos;
    }

    @Override
    public void setImagePath() {
        platform.setImagePath("/foot_tile.png");
    }

    @Override
    public void setHeight()
    {
        platform.setHeight(15);

    }

    @Override
    public void setXpos() {
        platform.setXpos(xpos);
    }

    @Override
    public void setYpos() {
        platform.setYpos(ypos);
    }

    @Override
    public void setWidth() {
        platform.setWidth(15);
    }

    @Override
    public Platform getPlatform() {
        return platform;
    }
}
