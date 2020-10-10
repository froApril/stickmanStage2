package stickman.model.entities;

import stickman.model.Strategy.Strategy;
import stickman.model.entities.Entity;

public class Cloud implements Entity {
    private double xpos;
    private double ypos;
    private String imagePath;
    private double height;
    private double width;
    private Layer layer;
    private double cloudVelocity;
    private boolean display;

    public Cloud() {
        this.layer = Layer.BACKGROUND;
        display = true;
    }

    @Override
    public String getImagePath() {
        return this.imagePath;
    }

    @Override
    public boolean collision(Entity entity, Strategy strategy) {
        return false;
    }

    @Override
    public double getXPos() {
        return this.xpos;
    }

    public void update(){
        if(this.cloudVelocity!=0){
            xpos+=cloudVelocity;
            if(xpos>640){
                xpos = 0;
            }
        }
    }

    @Override
    public double getYPos() {
        return this.ypos;
    }

    @Override
    public double getHeight() {
        return this.height;
    }

    @Override
    public double getWidth() {
        return this.width;
    }

    @Override
    public Layer getLayer() {
        return this.layer;
    }

    @Override
    public boolean getDisplay() {
        return display;
    }

    public void setXpos(double xpos) {
        this.xpos = xpos;
    }

    public void setYpos(double ypos) {
        this.ypos = ypos;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public void setCloudVelocity(double v){cloudVelocity = v;}

    @Override
    public void destroy() {
        display = false;
    }

}
