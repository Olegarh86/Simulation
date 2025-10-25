package simulation.entity;

import simulation.map.MapOfWorld;
import simulation.utils.config.Config;

import java.util.HashSet;
import java.util.Set;

public class CreatorOfRocks implements Creator {

    @Override
    public Set<Entity> createMultipleEntities(MapOfWorld map, Config config) {
        Set<Entity> entities = new HashSet<>();
        for (int i = 0; i < config.numberOfRocks; i++) {
            entities.add(new Rock());
        }
        return entities;
    }
}
