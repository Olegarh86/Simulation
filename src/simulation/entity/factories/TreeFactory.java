package simulation.entity.factories;

import simulation.entity.Entity;
import simulation.entity.Tree;
import simulation.world.MapOfWorld;
import simulation.utils.config.Config;

import java.util.HashSet;
import java.util.Set;

public class TreeFactory implements EntityFactory {

    @Override
    public Set<Entity> createMultipleEntities(MapOfWorld world, Config config) {
        Set<Entity> entities = new HashSet<>();
        for (int i = 0; i < config.numberOfTrees; i++) {
            entities.add(new Tree());
        }
        return entities;
    }
}
