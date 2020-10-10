package stickman.model.Director;

import stickman.model.Builder.EnemyBuilder;

public class EnemyDirector {
    public void createEnemy(EnemyBuilder enemyBuilder){
        enemyBuilder.setXpos();
        enemyBuilder.setYpos();
        enemyBuilder.setWidth();
        enemyBuilder.setHeight();
        enemyBuilder.setImagePath();
        enemyBuilder.setImagePath2();
        enemyBuilder.setRange();
        enemyBuilder.setLeftRange();
        enemyBuilder.setRightRange();
        enemyBuilder.setVelocity();
    }
}
