package stickman.model;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import stickman.model.entities.*;
import stickman.model.level.Level;
import stickman.model.level.LevelImpl;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GameEngineImpl implements GameEngine {
    private String heroSize;
    private double heroPox;
    private double cloudVelocity;
    private Level currentLevel;
    private Level startLevel;
    private List<Level> levels;
    private boolean LeftMoveFlag;
    private boolean RightMoveFlag;
    private boolean StopFlag;
    private int faceFlag=1;
    private boolean jump = false;

    private Hero hero;
    private List<Entity>clouds;
    private List<Entity>platforms;
    private Flag flag;
    private List<Entity>mushrooms;

    private int timer=0;

    public GameEngineImpl(String filename){
        startGame(filename);
    }

    public void startGame(String filename){
        JSONObject jobj  = JSON.parseObject(readJson(filename));
        initHero(jobj);
        initCloud(jobj);
        //test
        initPlatforms(jobj);
        initFlag(jobj);
        initMushrooms(jobj);
        initLevel(jobj);
    }

    private void initHero(JSONObject jobj){
        this.heroSize = jobj.getString("stickmanSize");
        JSONObject temp  = (JSONObject) jobj.get("stickmanPos");
        if(temp!=null){
            this.heroPox = temp.getDouble("x");
        }
        hero = new Hero(heroPox,300,heroSize);
    }

    private void initCloud(JSONObject jobj){
        this.cloudVelocity = jobj.getDouble("cloudVelocity");
        clouds = new ArrayList<>();
        clouds.add(new Cloud(20,80,"/cloud_1.png",40
                ,100,Entity.Layer.EFFECT,cloudVelocity));

    }

    private void initFlag(JSONObject jobj){
        JSONObject flagDetails = jobj.getJSONObject("flag");
        flag = new Flag(flagDetails.getInteger("x")
                ,flagDetails.getInteger("y"));
    }

    private void initLevel(JSONObject jobj){
        //currently there is only basic entity in levels
        JSONArray levels = jobj.getJSONArray("levels");
        if(levels!=null){
            //todo
        }
        currentLevel = new LevelImpl(hero,clouds,platforms,flag,mushrooms);
    }
    //test function
    private void initPlatforms(JSONObject jsonObject){
        jsonObject.toJSONString();
        JSONArray platformsJA = jsonObject.getJSONArray("platforms");
        platforms = new ArrayList<>();
        for(int i =0; i<platformsJA.size();i++){
            JSONObject object = platformsJA.getJSONObject(i);
            int num = (int) (object.getDouble("width")/10);
            for(int j =0;j<num;j++){
                platforms.add(new Platform(object.getDouble("x")+j*15
                        ,object.getDouble("y")
                        ,15
                        ,15));
            }
        }
    }

    private void initMushrooms(JSONObject jobj){
        mushrooms = new ArrayList<>();
        JSONArray mushroomsArray = jobj.getJSONArray("mushroom");
        for(int i =0 ;i<mushroomsArray.size();i++){
            JSONObject object = mushroomsArray.getJSONObject(i);
            mushrooms.add(new Mushroom(object.getInteger("x"),object.getInteger("y")));
        }
    }

    private String readJson(String filename){
        String res = "";
        try {
            File jsonFile = new File(filename);
            FileReader fileReader = new FileReader(jsonFile);
            Reader reader = new InputStreamReader(new FileInputStream(jsonFile),"utf-8");
            int ch = 0;
            StringBuffer sb = new StringBuffer();
            while ((ch = reader.read()) != -1) {
                sb.append((char) ch);
            }
            res = sb.toString();
            fileReader.close();
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            return res;
        }
    }

    @Override
    public Level getCurrentLevel() {
        return currentLevel;
    }

    @Override
    public void startLevel() {
        startGame("default.json");
    }

    @Override
    public boolean jump() {
        jump = hero.jump();
        return jump;
    }

    @Override
    public boolean moveLeft() {
        hero.moveLeft();
        LeftMoveFlag =true;
        RightMoveFlag =false;
        StopFlag = false;
        return true;
    }

    @Override
    public boolean moveRight() {
        hero.moveRight();
        LeftMoveFlag =false;
        RightMoveFlag =true;
        StopFlag = false;
        return true;
    }

    @Override
    public boolean stopMoving() {
        // if left==false, should face to right
        if(LeftMoveFlag){faceFlag = 0;}
        if(RightMoveFlag){faceFlag =1;}
        LeftMoveFlag = false;
        RightMoveFlag = false;
        StopFlag = true;
        return true;
    }

    @Override
    public void shot() {
        if(Hero.isStrength()){
            currentLevel.shot(faceFlag);
        }
    }

    @Override
    public void tick() {
        timer++;
        if(jump){
            if(faceFlag==0){
                hero.setImagePath("/ch_stand1.png");
            }
            else{
                hero.setImagePath("/ch_stand4.png");
            }
            jump();
        }
        if(RightMoveFlag){
            if(timer %40>=0 && timer%40<=10){
                hero.setImagePath("/ch_walk1.png");
            }
            else if(timer %40>10 && timer%40<=20 ){
                hero.setImagePath("/ch_walk2.png");
            }
            else if(timer %40>20 && timer%40<=30 ){
                hero.setImagePath("/ch_walk3.png");
            }
            else if(timer %40>30 ){
                hero.setImagePath("/ch_walk4.png");
            }
            hero.moveRight();
        }
        else if(LeftMoveFlag){
            if(timer %40>=0 && timer%40<=10){
                hero.setImagePath("/ch_walk5.png");
            }
            else if(timer %40>10 && timer%40<=20 ){
                hero.setImagePath("/ch_walk6.png");
            }
            else if(timer %40>20 && timer%40<=30 ){
                hero.setImagePath("/ch_walk7.png");
            }
            else if(timer %40>30 ){
                hero.setImagePath("/ch_walk8.png");
            }
            hero.moveLeft();
        }
        else{
            if(faceFlag ==0){
                if(timer %30>=0 && timer%30<10){
                    hero.setImagePath("/ch_stand4.png");
                }
                else if(timer %30>=10 && timer%30<20){
                    hero.setImagePath("/ch_stand5.png");
                }
                else if(timer %30>=20){
                    hero.setImagePath("/ch_stand6.png");
                }
            }
            else{
                if(timer %30>=0 && timer%30<10){
                    hero.setImagePath("/ch_stand1.png");
                }
                else if(timer %30>=10 && timer%30<20){
                    hero.setImagePath("/ch_stand2.png");
                }
                else if(timer %30>=20){
                    hero.setImagePath("/ch_stand3.png");
                }

            }
        }

    }
}
