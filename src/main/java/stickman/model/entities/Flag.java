package stickman.model.entities;

import stickman.model.Strategy.Strategy;

public class Flag implements Entity {

    private static Flag flag = null;
    private double xpos;
    private double ypos;
    private String imagePath = "/flag.png";
    private double height;
    private double width;
    private Layer layer = Layer.EFFECT;
    private Strategy intersectionAlgorithm;

    public Flag(double xpos,double ypos){
        this.xpos = xpos;
        this.ypos = ypos;
        this.height = 15;
        this.width = 10;
        flag = this;
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

    public boolean collision(Entity entity,Strategy strategy){
        this.intersectionAlgorithm = strategy;
        return intersectionAlgorithm.intersect(entity,this);
    }

    public static Flag getFlagInstance(){
        if(flag ==null){
            return null;
        }
        return flag;
    }
}
