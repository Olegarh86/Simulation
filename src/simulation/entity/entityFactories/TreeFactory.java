package simulation.entity.entityFactories;

import simulation.entity.Entity;
import simulation.entity.Tree;
import simulation.config.Config;

public class TreeFactory implements EntityFactory {

    @Override
    public Entity create(Config config) {
        return new Tree();
    }
}
