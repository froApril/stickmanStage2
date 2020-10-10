package stickman.model;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import stickman.model.Builder.*;
import stickman.model.Director.*;
import stickman.model.entities.*;
import stickman.model.level.Level;
import stickman.model.level.LevelImpl;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GameEngineImpl implements GameEngine {
    private String heroSize;
    private double heroPox;
    private Level currentLevel;
    private String currentFile;
    private Level startLevel;
    private List<Level> levels;
    private boolean LeftMoveFlag;
    private boolean RightMoveFlag;
    private boolean StopFlag;
    private int faceFlag=1;
    private boolean jump = false;
    private String nextLevelFile = "";

    private EntityFactory entityFactory;

    private Hero hero;
    private List<Entity>clouds;
    private List<Entity>platforms;
    private Flag flag;
    private List<Entity>mushrooms;
    private List<Entity>enemies;

    private int timer=0;

    public GameEngineImpl(String filename)
    {
        entityFactory = new EntityFactory();
        this.currentFile = filename;
        startGame(filename);
    }

    public void startGame(String filename){
        JSONObject jobj  = JSON.parseObject(readJson(filename));
        initHero(jobj);
        initCloud(jobj);
        initPlatforms(jobj);
        initFlag(jobj);
        initMushrooms(jobj);
        initEnemies(jobj);
        initLevel(jobj);
    }

    private void initHero(JSONObject jobj){
        this.heroSize = jobj.getString("stickmanSize");
        JSONObject temp  = (JSONObject) jobj.get("stickmanPos");
        if(temp!=null){
            this.heroPox = temp.getDouble("x");
        }
        HeroDirector heroDirector = new HeroDirector();
        HeroBuilder builder = new HeroBuilderImpl(heroPox,300,heroSize);
        heroDirector.createHero(builder);
        hero = builder.getHero();
    }

    private void initCloud(JSONObject jobj){
        Object temp = jobj.get("cloudVelocity");
        if(temp==null){
            return;
        }
        double cloudVelocity = jobj.getDouble("cloudVelocity");
        clouds = new ArrayList<>();
        CloudDirector cloudDirector = new CloudDirector();
        CloudBuilder builder = new CloudBuilderImpl(20,80,cloudVelocity);
        cloudDirector.createCloud(builder);
        clouds.add(builder.getCloud());

    }

    private void initFlag(JSONObject jobj){
        JSONObject flagDetails = jobj.getJSONObject("flag");
        if(flagDetails!=null){

            FlagDirector flagDirector = new FlagDirector();
            FlagBuilder builder = new FlagBuilderImpl(flagDetails.getInteger("x")
                    ,flagDetails.getInteger("y"));
            flagDirector.createFlag(builder);
            flag = builder.getFlag();
        }

    }

    private void initLevel(JSONObject jobj){
        //currently there is only basic entity in levels
        nextLevelFile = jobj.getString("level");
        currentLevel = new LevelImpl(hero,clouds,platforms,flag,mushrooms,enemies);
    }
    //test function
    private void initPlatforms(JSONObject jsonObject){
        JSONArray platformsJA = jsonObject.getJSONArray("platforms");
        platforms = new ArrayList<>();
        if(platformsJA==null){
            return;
        }
        for(int i =0; i<platformsJA.size();i++){
            JSONObject object = platformsJA.getJSONObject(i);
            int num = (int) (object.getDouble("width")/10);
            for(int j =0;j<num;j++){
                PlatformDirector platformDirector = new PlatformDirector();
                PlatformBuilder platformBuilder = new PlatformBuilderImpl(
                        object.getDouble("x")+j*15,object.getDouble("y")
                );
                platformDirector.createPlatform(platformBuilder);
                platforms.add(platformBuilder.getPlatform());
            }
        }
    }

    private void initMushrooms(JSONObject jobj){
        mushrooms = new ArrayList<>();
        JSONArray mushroomsArray = jobj.getJSONArray("mushroom");
        if(mushroomsArray==null){
            return;
        }
        for(int i =0 ;i<mushroomsArray.size();i++){
            JSONObject object = mushroomsArray.getJSONObject(i);
            MushroomDirector mushroomDirector = new MushroomDirector();
            MushroomsBuilder mushroomsBuilder = new MushroomsBuilderImpl(
                    object.getInteger("x"),object.getInteger("y")
            );
            mushroomDirector.createMushroom(mushroomsBuilder);
            mushrooms.add(mushroomsBuilder.getMushroom());
        }
    }

    private void initEnemies(JSONObject jobj){
        JSONArray enemiesJA = jobj.getJSONArray("enemy");
        enemies = new ArrayList<>();
        if(enemiesJA==null){
            return;
        }
        for(int i=0;i<enemiesJA.size();i++){
            JSONObject object = enemiesJA.getJSONObject(i);
            EnemyDirector enemyDirector = new EnemyDirector();
            EnemyBuilder enemyBuilder = new EnemyBuilderImpl(
                    object.getInteger("x")
                    ,object.getInteger("y")
                    ,1
                    ,object.getInteger("range")
                    ,object.getString("image1")
                    ,object.getString("image2")

            );
            enemyDirector.createEnemy(enemyBuilder);
            enemies.add(enemyBuilder.getEnemy());
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
        startGame(currentFile);
    }

    public void nextLevel(){
        startGame(nextLevelFile);
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
            currentLevel.shot(LeftMoveFlag);
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
