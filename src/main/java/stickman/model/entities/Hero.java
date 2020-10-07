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
    private double groundLevel;
    private boolean JumpFlag = false;

    public Hero(double xpos,double ypos,String size){
        this.xpos = xpos;
        this.ypos = ypos;
        this.groundLevel = ypos;
        if(size.equals("normal")){
            this.height = 25;
            this.width = 20;
        }
        else if(size.equals("large")){
            this.height =50;
            this.width = 25;
        }
        HERO_INSTANCE = this;

    }

    public static Hero getHeroInstance(){
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

    public void jumpFastEnd(){
        fastEndFlag = true;
        jumpPeriod = 0;
    }

    public void resetFastEnd(){
        fastEndFlag = false;
        jumpPeriod= 0;
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
        JumpFlag = true;
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
                JumpFlag = false;
                resetFastEnd();
                return false;
            }
            //down
            if(ypos+1<=300){
                ypos+=1;
                return true;
            }
            if(ypos==groundLevel){
                fallDown = false;
            }
        }
        JumpFlag = false;
        jumpPeriod = 0;
        return false;
    }

    public void setImagePath(String image){
        this.imagePath = image;
    }

    public void fallFromCurrentStage(){
        ypos++;
    }

    public boolean onTheGround(){
        return ypos == groundLevel;
    }

    public boolean isJumping(){
        return JumpFlag;
    }


    public boolean UpCollisionWithPlatform(Entity entity){
        double current_height = height+ypos;
        if(entity.getYPos()==current_height &&
                (xpos>=entity.getXPos() && xpos<=entity.getXPos()+width)){
            if(fallDown){
                jumpFastEnd();
            }
            return true;
        }
        return false;
    }

    public boolean UnderCollisionWithPlatform(Entity entity){
        if(entity.getYPos()+entity.getHeight() == ypos
                &&(xpos>=entity.getXPos() && xpos<=entity.getXPos()+width)){
            if(!fallDown){
                fallDown = true;
            }
            return true;
        }
        return false;
    }

}
