package stickman.view;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import stickman.model.Strategy.GeneralIntersect;
import stickman.model.Strategy.PlatformUnderIntersect;
import stickman.model.Strategy.PlatformUpIntersect;
import stickman.model.entities.Entity;
import stickman.model.GameEngine;
import stickman.model.entities.Flag;
import stickman.model.entities.Hero;

import java.util.ArrayList;
import java.util.List;

public class GameWindow {
    private final int width;
    private Scene scene;
    private Pane pane;
    private GameEngine model;
    private List<EntityView> entityViews;
    private BackgroundDrawer backgroundDrawer;

    private double xViewportOffset = 0.0;
    private static final double VIEWPORT_MARGIN = 100.0;

    private double baseYPos;
    private Hero hero;

    public GameWindow(GameEngine model, int width, int height) {
        this.model = model;
        this.pane = new Pane();
        this.width = width;
        this.scene = new Scene(pane, width, height);

        this.entityViews = new ArrayList<>();

        KeyboardInputHandler keyboardInputHandler = new KeyboardInputHandler(model);

        scene.setOnKeyPressed(keyboardInputHandler::handlePressed);
        scene.setOnKeyReleased(keyboardInputHandler::handleReleased);

        this.backgroundDrawer = new BlockedBackground();
        baseYPos = model.getCurrentLevel().getHeroY();

        backgroundDrawer.draw(model, pane);
    }

    public Scene getScene() {
        return this.scene;
    }

    public void run() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(17),
                t -> this.draw()));

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void draw() {
        model.tick();

        List<Entity> entities = model.getCurrentLevel().getEntities();

        for (EntityView entityView: entityViews) {
            entityView.markForDelete();
        }
        hero = Hero.getHeroInstance();
        Flag flag = Flag.getFlagInstance();

        double heroXPos = model.getCurrentLevel().getHeroX();
        double heroYPos = model.getCurrentLevel().getHeroY();

        //设置hero一个状态字段：是否在jump状态中，如果在jump状态中则将所有的entity
        //ypos向下移动jump个size
        heroXPos -= xViewportOffset;
        double yPosToChange = baseYPos -heroYPos;
        if (heroXPos < VIEWPORT_MARGIN) {
            if (xViewportOffset >= 0) { // Don't go further left than the start of the level
                xViewportOffset -= VIEWPORT_MARGIN - heroXPos;
                if (xViewportOffset < 0) {
                    xViewportOffset = 0;
                }
            }
        } else if (heroXPos > width - VIEWPORT_MARGIN) {
            xViewportOffset += heroXPos - (width - VIEWPORT_MARGIN);
        }

        yPosToChange = (yPosToChange>100)?50:0;
        //更新背景
        backgroundDrawer.update(xViewportOffset,yPosToChange);

        //更新内容
        for (Entity entity: entities) {

            boolean notFound = true;
            for (EntityView view: entityViews) {
                if (view.matchesEntity(entity)) {
                    notFound = false;
                    if(!entity.getDisplay()){
                        view.markForDelete();
                        continue;
                    }
                    entity.update();
                    view.update(xViewportOffset,yPosToChange);
                    break;
                }
            }
            if (notFound) {
                if(entity.getDisplay()){
                    EntityView entityView = new EntityViewImpl(entity);
                    entityViews.add(entityView);
                    pane.getChildren().add(entityView.getNode());
                }
            }
        }

        //mushroom
        checkMushroom(hero);
        //enemy
        checkEnemy();
        //hero
        checkHero(entities, hero);

        //win
        if(flag.collision(hero,new GeneralIntersect())){
            model.nextLevel();
        }

        //删除内容
        for (EntityView entityView: entityViews) {
            if (entityView.isMarkedForDelete()) {
                pane.getChildren().remove(entityView.getNode());
            }
        }
        entityViews.removeIf(EntityView::isMarkedForDelete);
    }

    private void checkMushroom(Hero hero){
        model.getCurrentLevel().checkHeroMushroomCollision(hero);
    }

    private void checkEnemy(){
        model.getCurrentLevel().checkEnemyBulletCollision();
    }

    private void restart(){
        hero.HeroReset();
        model.startLevel();
    }

    private void checkHero(List<Entity> entities, Hero hero){
//        System.out.println(hero.getYPos());
        //
        if(model.getCurrentLevel().checkHeroEnemyCollision()){
            restart();
        }
        //跳跃碰撞
        boolean collisionUpFlag = false;
        for(Entity entity : entities){
            if(entity.equals(hero)){
                continue;
            }
            else{
//                collisionUpFlag |=  hero.UpCollisionWithPlatform(entity);
                collisionUpFlag |= hero.collision(entity,new PlatformUpIntersect());
                hero.collision(entity,new PlatformUnderIntersect());
//                hero.UnderCollisionWithPlatform(entity);
            }
        }
        if(!collisionUpFlag && !hero.onTheGround() && !hero.isJumping()){
            hero.fallFromCurrentStage();
        }
    }
}
