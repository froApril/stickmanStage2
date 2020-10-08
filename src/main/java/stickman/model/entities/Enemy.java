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

    public Enemy(double xpos,double ypos,String imagePath1
            ,String imagePath2,double range){
        this.xpos = xpos;
        this.ypos = ypos;
        this.display = true;
        this.imagePath1 = imagePath1;
        this.imagePath2 = imagePath2;
        height = 20;
        width = 20;
        layer = Layer.EFFECT;
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

    @Override
    public boolean collision(Entity entity, Strategy strategy) {
        this.strategy = strategy;
        return strategy.intersect(entity,this);
    }

    public void destroy(){
        display = false;
    }
}
