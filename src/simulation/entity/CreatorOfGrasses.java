package simulation.entity;

import simulation.utils.Config;

import java.util.*;

public class CreatorOfGrasses implements Creator {

    @Override
    public Set<Entity> createMultipleEntities(Config config) {
        Set<Entity> entities = new HashSet<>();
        for (int i = 0; i < config.numberOfGrasses; i++) {
            entities.add(new Grass());
        }
        return entities;
    }

    @Override
    public Entity createEntity(Config config) {
        return new Grass();
    }
}
