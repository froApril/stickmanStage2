package stickman.model.entities;

import stickman.model.entities.Entity;

public class Hero implements Entity {
    private static Hero HERO_INSTANCE;
    private double xpos;
    private double ypos;
    private String imagePath="/ch_stand1.png";
    private double height;
    private double width;
    private Layer layer = Layer.FOREGROUND;
    private int jumpHeight = 100;
    private int jumpPeriod = 0;
    private boolean fallDown = false;


    private Hero(double xpos,double ypos,String size){
        this.xpos = xpos;
        this.ypos = ypos;
        if(size.equals("normal")){
            this.height = 25;
            this.width = 20;
        }
        else if(size.equals("large")){
            this.height =50;
            this.width = 25;
        }

    }

    public static Hero getHeroInstance(double xpos,double ypos,String size){
        if(HERO_INSTANCE == null){
            synchronized (Hero.class){
                if(HERO_INSTANCE==null){
                    HERO_INSTANCE = new Hero(xpos,ypos,size);
                }
            }
        }
        return HERO_INSTANCE;
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

    public void moveLeft(){
        if(xpos-3>=0){
            xpos -=3;
        }
    }
    public void moveRight(){
        if(xpos+3<=640){
            xpos+=3;
        }
    }

    public boolean jump(){
        if(!fallDown){
            //up
            if(ypos-2>=0 && jumpPeriod+2<=jumpHeight){
                jumpPeriod+=2;
                ypos-=2;
            }
            else if(jumpHeight == jumpPeriod){
                fallDown = true;
            }
            return true;
        }
        else{
            //down
            if(ypos+2<=300 && jumpPeriod-2>=0){
                jumpPeriod-=2;
                ypos+=2;
                return true;
            }
            if(jumpPeriod==0){
                fallDown = false;
            }
        }
        return false;
    }

    public void setImagePath(String image){
        this.imagePath = image;
    }

}
