package stickman.model.entities;

import stickman.model.Strategy.Strategy;

public class Platform implements Entity {
    private double xpos;
    private double ypos;
    private String imagePath;
    private double height;

    private double width;
    private Layer layer = Layer.EFFECT;

    public Platform(double xpos, double ypos, double height
            ,double width){
        this.xpos = xpos;
        this.ypos = ypos;
        this.imagePath = "/foot_tile.png";
        this.height = height;
        this.width = width;
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
        return true;
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
