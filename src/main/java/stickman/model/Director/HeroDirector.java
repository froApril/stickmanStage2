package stickman.model.Director;

import stickman.model.Builder.HeroBuilder;
import stickman.model.Builder.HeroBuilderImpl;

public class HeroDirector {

    public void createHero(HeroBuilder heroBuilder){
        heroBuilder.setSize();
        heroBuilder.setXpos();
        heroBuilder.setYpos();
        heroBuilder.setGroundLevel();
    }
}
