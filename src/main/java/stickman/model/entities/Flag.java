package stickman.model.entities;

import stickman.model.Strategy.Strategy;

public class Flag implements Entity {

    private static Flag flag = null;
    private double xpos;
    private double ypos;
    private String imagePath;
    private double height;
    private double width;
    private Layer layer = Layer.EFFECT;
    private Strategy intersectionAlgorithm;
    private boolean display;

    public Flag() {
        flag = this;
        display = true;
        this.height = 15;
        this.width = 10;
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
    public void update() {

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

    public boolean collision(Entity entity, Strategy strategy){
        this.intersectionAlgorithm = strategy;
        if(intersectionAlgorithm.intersect(entity,this)){
            display = false;
        }
        return intersectionAlgorithm.intersect(entity,this);
    }

    public static Flag getFlagInstance(){
        if(flag ==null){
            return null;
        }
        return flag;
    }

    @Override
    public boolean getDisplay() {
        return display;
    }

    @Override
    public void destroy() {
        display = false;
    }
}
