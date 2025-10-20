package simulation.entity;

import simulation.utils.Config;

import java.util.HashSet;
import java.util.Set;

public class CreatorOfTrees implements Creator {

    @Override
    public Set<Entity> createMultipleEntities(Config config) {
        Set<Entity> entities = new HashSet<>();
        for (int i = 0; i < config.numberOfTrees; i++) {
            entities.add(new Tree());
        }
        return entities;
    }

    @Override
    public Entity createEntity(Config config) {
        return new Tree();
    }
}
