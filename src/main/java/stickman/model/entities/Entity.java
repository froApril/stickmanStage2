package stickman.model.entities;

import stickman.model.Strategy.Strategy;

public interface Entity {
    String getImagePath();
    double getXPos();
    double getYPos();
    double getHeight();
    double getWidth();
    Layer getLayer();
    boolean getDisplay();
    void destroy();
    void update();
    boolean collision(Entity entity, Strategy strategy);
    enum Layer{
        BACKGROUND, FOREGROUND, EFFECT
    }

}
