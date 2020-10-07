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
    private boolean fastEndFlag = false;


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
    public static Hero getHeroInstance(){
        if(HERO_INSTANCE!=null){
            return HERO_INSTANCE;
        }
        return getHeroInstance(20,300,"normal");
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

    public void jumpFastEnd(){
        fastEndFlag = true;
        jumpPeriod = 0;
    }

    public void resetFastEnd(){
        fastEndFlag = false;
    }

    public void moveLeft(){
        if(xpos-1>=0){
            xpos -=1;
        }
    }
    public void moveRight(){
        if(xpos+1<=640){
            xpos+=1;
        }
    }

    public boolean jump(){
        if(!fallDown){
            //up
            if(ypos-1>=0 && jumpPeriod+1<=jumpHeight){
                jumpPeriod+=1;
                ypos-=1;
            }
            else if(jumpHeight == jumpPeriod){
                fallDown = true;
            }
            return true;
        }
        else{
            if(fastEndFlag){
                fallDown = false;
                resetFastEnd();
                return false;
            }
            //down
            if(ypos+1<=300 && jumpPeriod-1>=0){
                jumpPeriod-=1;
                ypos+=1;
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

    public void collisionWithPlatform(Entity entity){
        double current_height = height+ypos;
        if(entity.getYPos()==current_height &&
                (xpos>=entity.getXPos() && xpos<=entity.getXPos()+width)){
            if(fallDown){
                jumpFastEnd();
            }
        }
    }

}
