package stickman.model.entities;

import stickman.model.Strategy.Strategy;

public class Enemy implements Entity {
    private double xpos;
    private double ypos;
    private String imagePath1;
    private String imagePath2;
    private double height;
    private double width;
    private Layer layer;
    private boolean display;
    private double range;
    private double left_range;
    private double right_range;
    private int v =1;
    private Strategy strategy;

    public Enemy() {
        this.display = true;
        layer = Layer.EFFECT;
    }

    public void config(double xpos,double ypos,String imagePath1
            ,String imagePath2,double range){
        this.xpos = xpos;
        this.ypos = ypos;
        this.imagePath1 = imagePath1;
        this.imagePath2 = imagePath2;
        this.range = range;
        this.left_range = xpos;
        this.right_range = xpos+range;
    }

    @Override
    public String getImagePath() {
        if(xpos %2 ==0){
            return imagePath1;
        }
        return imagePath2;
    }

    @Override
    public double getXPos() {
        return this.xpos;
    }

    @Override
    public void update() {
        if(v<0 && xpos<=left_range){
            v = 1;
        }
        else if(v>0 && xpos>=right_range){
            v=-1;
        }
        xpos+=v*(0.5);
    }

    @Override
    public double getYPos() {
        return ypos;
    }

    @Override
    public double getHeight() {
        return height;
    }

    @Override
    public double getWidth() {
        return width;
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

    public void setImagePath1(String imagePath1) {
        this.imagePath1 = imagePath1;
    }

    public void setImagePath2(String imagePath2) {
        this.imagePath2 = imagePath2;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public void setRange(double range) {
        this.range = range;
    }

    public void setLeft_range(double left_range) {
        this.left_range = left_range;
    }

    public void setRight_range(double right_range) {
        this.right_range = right_range;
    }

    public void setV(int v) {
        this.v = v;
    }

    @Override
    public boolean collision(Entity entity, Strategy strategy) {
        this.strategy = strategy;
        return strategy.intersect(entity,this);
    }

    public void destroy(){
        display = false;
    }
}
