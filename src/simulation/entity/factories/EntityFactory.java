package simulation.entity.factories;

import simulation.entity.Entity;
import simulation.world.MapOfWorld;
import simulation.utils.config.Config;

import java.util.Set;

public interface EntityFactory {
    Set<Entity> createMultipleEntities(MapOfWorld world, Config config);
}

