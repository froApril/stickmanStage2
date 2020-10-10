package stickman.model.Director;

import stickman.model.Builder.FlagBuilder;

public class FlagDirector {
    public void createFlag(FlagBuilder flagBuilder){
        flagBuilder.setHeight();
        flagBuilder.setImagePath();
        flagBuilder.setWidth();
        flagBuilder.setXpos();
        flagBuilder.setYpos();
    }
}
