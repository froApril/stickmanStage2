package stickman.model.entities;


import static stickman.model.entities.EntityType.HERO;

public class EntityFactory {
    public Entity getEntity(EntityType entityType){
        Entity res = null;
        switch (entityType){
            case HERO:
                 res = new Hero();
                 break;
            case FLAG:
                 res = new Flag();
                 break;
            case CLOUD:
                 res = new Cloud();
                 break;
            case ENEMY:
                 res = new Enemy();
                 break;
            case BULLET:
                 res = new Bullet();
                 break;
            case MUSHROOM:
                 res = new Mushroom();
                 break;
            case PLATFORM:
                 res = new Platform();
                 break;
            default:
                 break;
        }
        return res;
    }
}
