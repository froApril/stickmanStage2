package stickman.model.entities;

import stickman.model.Strategy.Strategy;
import stickman.model.Strategy.StrategyType;

public class Hero implements Entity {
    private static Hero HERO_INSTANCE;
    private double xpos;
    private double ypos;
    private String imagePath;
    private double height;
    private double width;
    private Layer layer = Layer.FOREGROUND;
    private int jumpHeight = 100;
    private int jumpPeriod = 0;
    private boolean fallDown = false;
    private boolean fastEndFlag = false;
    private double groundLevel;
    private boolean JumpFlag = false;
    private String size;

    private static boolean BULLET_SWITCH = false;

    public Hero(){
        HERO_INSTANCE = this;
    }

    public void setXpos(double xpos) {
        this.xpos = xpos;
    }

    public void setYpos(double ypos) {
        this.ypos = ypos;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void normalSize(){
        this.height = 25;
        this.width = 20;
    }

    public void largeSize(){
        this.height =50;
        this.width = 25;
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

    @Override
    public boolean getDisplay() {
        return true;
    }

    public void setGroundLevel(double ypos){
        groundLevel = ypos;
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

    public void fallFromCurrentStage()
    {
        ypos++;
    }

    public boolean onTheGround(){
        return ypos == groundLevel;
    }

    public boolean isJumping(){
        return JumpFlag;
    }

    @Override
    public boolean collision(Entity entity, Strategy strategy) {
        if(strategy.intersect(this,entity)){
            if(fallDown && strategy.getType()== StrategyType.PLATFORM_UP){
                jumpFastEnd();
            }
            else if(!fallDown && strategy.getType() == StrategyType.PLATFORM_BOTTOM){
                fallDown = true;
            }
            return true;
        }
        return false;
    }


    public static void getStrength(){
        if(!BULLET_SWITCH){
            BULLET_SWITCH = true;
        }
    }

    public static boolean isStrength(){
        return BULLET_SWITCH;
    }

    public static void HeroReset(){
        BULLET_SWITCH = false;
    }

    @Override
    public void destroy() {

    }
    public void update(){

    }

}
