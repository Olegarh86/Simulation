package simulation.entity.entityFactories;

import simulation.entity.Entity;
import simulation.entity.Grass;
import simulation.config.Config;

public class GrassFactory implements EntityFactory {

    @Override
    public Entity create(Config config) {
        return new Grass();
    }
}
