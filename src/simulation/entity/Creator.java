package simulation.entity;

import simulation.map.MapOfWorld;
import simulation.utils.config.Config;

import java.util.Set;

public interface Creator {

    Set<Entity> createMultipleEntities(MapOfWorld map, Config config);
}

