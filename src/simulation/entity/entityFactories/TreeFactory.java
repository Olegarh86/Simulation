package simulation.entity.entityFactories;

import simulation.entity.Entity;
import simulation.entity.Tree;
import simulation.world.MapOfWorld;
import simulation.config.Config;

public class TreeFactory implements EntityFactory {

    @Override
    public Entity create(MapOfWorld map, Config config) {
        return new Tree();
    }
}
