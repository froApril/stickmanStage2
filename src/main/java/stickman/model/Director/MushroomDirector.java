package stickman.model.Director;

import stickman.model.Builder.MushroomsBuilder;

public class MushroomDirector {
    public void createMushroom(MushroomsBuilder mushroomsBuilder){
        mushroomsBuilder.setXpos();
        mushroomsBuilder.setYpos();
        mushroomsBuilder.setHeight();
        mushroomsBuilder.setWidth();
        mushroomsBuilder.setImagePath();
    }
}
