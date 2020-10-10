package stickman.model.Builder;

import stickman.model.entities.Cloud;
import stickman.model.entities.EntityFactory;
import stickman.model.entities.EntityType;

public class CloudBuilderImpl extends CloudBuilder {
    private Cloud cloud;
    private EntityFactory entityFactory =new EntityFactory();
    double xpos;
    double ypos;
    double v;
    public CloudBuilderImpl(double xpos, double ypos,double v){
        cloud = (Cloud) entityFactory.getEntity(EntityType.CLOUD);
        this.xpos = xpos;
        this.ypos = ypos;
        this.v = v;
    }


    @Override
    public void setXpos() {
        cloud.setXpos(xpos);
    }

    @Override
    public void setYpos() {
        cloud.setYpos(ypos);
    }

    @Override
    public void setImagePath() {
        cloud.setImagePath("/cloud_1.png");
    }

    @Override
    public void setHeight() {
        cloud.setHeight(40);
    }

    @Override
    public void setWidth() {
        cloud.setWidth(100);
    }

    public void setCloudVelocity(){
        cloud.setCloudVelocity(v);
    }

    @Override
    public Cloud getCloud() {
        return cloud;
    }
}
