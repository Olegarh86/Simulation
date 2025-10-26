package simulation.entity.factories;

import simulation.entity.Entity;
import simulation.entity.Grass;
import simulation.world.MapOfWorld;
import simulation.utils.config.Config;

import java.util.*;

public class GrassFactory implements EntityFactory {

    @Override
    public Set<Entity> createMultipleEntities(MapOfWorld world, Config config) {
        Set<Entity> entities = new HashSet<>();
        for (int i = 0; i < config.numberOfGrasses; i++) {
            entities.add(new Grass());
        }
        return entities;
    }
}
