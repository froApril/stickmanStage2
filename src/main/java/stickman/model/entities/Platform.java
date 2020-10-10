package stickman.model.entities;

import stickman.model.Strategy.Strategy;

public class Platform implements Entity {
    private double xpos;
    private double ypos;
    private String imagePath;
    private double height;
    private boolean display;
    private double width;
    private Layer layer = Layer.EFFECT;


    public Platform() {
        this.display = true;
    }


    @Override
    public String getImagePath() {
        return this.imagePath;
    }

    @Override
    public double getXPos() {
        return this.xpos;
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

    public void setDisplay(boolean display) {
        this.display = display;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    @Override
    public void destroy() {

    }

    public void update(){}

    @Override
    public boolean collision(Entity entity, Strategy strategy) {
        return false;
    }
}
