package stickman.view;

import javafx.scene.Node;
import stickman.model.entities.Entity;

public interface EntityView {
    void update(double xViewportOffset,double yPosToChange);

    boolean matchesEntity(Entity entity);

    void markForDelete();

    Node getNode();

    boolean isMarkedForDelete();
}
