package stickman.model.Builder;

import stickman.model.Factory.CloudFactory;
import stickman.model.Factory.EntityFactory;
import stickman.model.entities.Cloud;

public class CloudBuilderImpl extends CloudBuilder {
    private Cloud cloud;
    private EntityFactory entityFactory =new CloudFactory();
    double xpos;
    double ypos;
    double v;
    public CloudBuilderImpl(double xpos, double ypos,double v){
        cloud = (Cloud) entityFactory.getEntity();
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
