package stickman.model.Director;

import stickman.model.Builder.CloudBuilder;

public class CloudDirector {

    public void createCloud(CloudBuilder cloudBuilder){
        cloudBuilder.setHeight();
        cloudBuilder.setImagePath();
        cloudBuilder.setXpos();
        cloudBuilder.setWidth();
        cloudBuilder.setYpos();
        cloudBuilder.setCloudVelocity();
    }
}
