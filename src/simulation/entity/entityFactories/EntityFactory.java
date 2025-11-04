package simulation.entity.entityFactories;

import simulation.entity.Entity;
import simulation.config.Config;

public interface EntityFactory {
    Entity create(Config config);
}


