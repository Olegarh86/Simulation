package simulation.entity;

import simulation.map.MapOfWorld;
import simulation.utils.config.Config;

import java.util.*;

public class CreatorOfGrasses implements Creator {

    @Override
    public Set<Entity> createMultipleEntities(MapOfWorld map, Config config) {
        Set<Entity> entities = new HashSet<>();
        for (int i = 0; i < config.numberOfGrasses; i++) {
            entities.add(new Grass());
        }
        return entities;
    }
}
