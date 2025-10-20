package simulation.entity;

import simulation.utils.Config;

import java.util.HashSet;
import java.util.Set;

public class CreatorOfRocks implements Creator {

    @Override
    public Set<Entity> createMultipleEntities(Config config) {
        Set<Entity> entities = new HashSet<>();
        for (int i = 0; i < config.numberOfRocks; i++) {
            entities.add(new Rock());
        }
        return entities;
    }

    @Override
    public Entity createEntity(Config config) {
        return new Rock();
    }
}
