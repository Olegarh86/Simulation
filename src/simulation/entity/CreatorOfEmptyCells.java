package simulation.entity;

import simulation.map.MapOfWorld;
import simulation.utils.config.Config;

import java.util.HashSet;
import java.util.Set;

public class CreatorOfEmptyCells implements Creator {

    @Override
    public Set<Entity> createMultipleEntities(MapOfWorld map, Config config) {
        return new HashSet<>();
    }
}

