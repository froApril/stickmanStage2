package stickman.model.entities;

import stickman.model.Strategy.Strategy;

public class Mushroom implements Entity {
    private double xpos;
    private double ypos;
    private String imagePath;
    private double height;
    private double width;
    private Layer layer;
    Strategy strategy = null;
    private boolean display;

    public Mushroom(double xpos, double ypos){
        this.xpos = xpos;
        this.ypos = ypos;
        this.imagePath = "/mushroom.png";
        this.height = 10;
        this.width = 10;
        layer = Layer.BACKGROUND;
        display = true;
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

    public boolean collision(Entity entity, Strategy strategy){
        this.strategy = strategy;
        if(strategy.intersect(entity,this)){
            destroy();
        }
        return strategy.intersect(entity,this);
    }
    @Override
    public void destroy() {
        display = false;
    }

    public void update(){}

}
