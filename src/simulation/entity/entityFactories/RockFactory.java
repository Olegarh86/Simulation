package simulation.entity.entityFactories;

import simulation.entity.Entity;
import simulation.entity.Rock;
import simulation.config.Config;

public class RockFactory implements EntityFactory {

    @Override
    public Entity create(Config config) {
        return new Rock();
    }
}
