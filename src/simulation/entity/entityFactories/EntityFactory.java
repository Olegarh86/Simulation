package simulation.entity.entityFactories;

import simulation.entity.Entity;
import simulation.world.MapOfWorld;
import simulation.config.Config;

public interface EntityFactory {
    Entity create(MapOfWorld map, Config config);
}


