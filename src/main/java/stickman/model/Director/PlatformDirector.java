package stickman.model.Director;

import stickman.model.Builder.PlatformBuilder;

public class PlatformDirector {
    public void createPlatform(PlatformBuilder platformBuilder){
        platformBuilder.setHeight();
        platformBuilder.setWidth();
        platformBuilder.setImagePath();
        platformBuilder.setXpos();
        platformBuilder.setYpos();
    }
}
