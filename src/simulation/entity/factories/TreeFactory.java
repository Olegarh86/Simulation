package simulation.entity.factories;

import simulation.entity.Entity;
import simulation.entity.Tree;
import simulation.world.MapOfWorld;
import simulation.utils.config.Config;

public class TreeFactory implements EntityFactory {

    @Override
    public Entity create(MapOfWorld map, Config config) {
        return new Tree();
    }
}
