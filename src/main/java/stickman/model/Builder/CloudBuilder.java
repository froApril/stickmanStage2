package stickman.model.Builder;

import stickman.model.entities.Cloud;

public abstract class CloudBuilder extends EntityBuilder {

    public abstract void setCloudVelocity();

    public abstract Cloud getCloud();
}
