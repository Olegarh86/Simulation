package simulation.entity;

import simulation.utils.Config;

public interface Creator {

    abstract Iterable<Entity> createMultipleEntities(Config config);
    abstract Entity createEntity(Config config);
}

