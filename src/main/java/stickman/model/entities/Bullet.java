package stickman.model.entities;

import stickman.model.Strategy.Strategy;

public class Bullet implements Entity {

    private double xpos;
    private double ypos;
    private String imagePath;
    private double height;
    private double width;
    private Layer layer;
    private int direction;
    private boolean display = false;

    public Bullet(){
        this.imagePath = "/bullet.png";
        this.height = 15;
        this.width = 15;
        this.layer = Layer.EFFECT;
    }

    @Override
    public String getImagePath() {
        return this.imagePath;
    }

    @Override
    public double getXPos() {
        if(display){
            move();
        }
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
    public boolean collision(Entity entity, Strategy strategy) {
        return false;
    }

    public void shot(double xpos,double ypos,int direction){
        this.xpos = xpos;
        this.ypos = ypos;
        this.direction = direction;
        display = true;
    }

    @Override
    public boolean getDisplay() {
        return display;
    }

    public void move(){
        xpos+=2*direction;
        if(xpos<0 || xpos>640){
            display = false;
        }
    }
}
