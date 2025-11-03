package simulation.entity.entityFactories;

import simulation.entity.Entity;
import simulation.entity.Grass;
import simulation.world.MapOfWorld;
import simulation.config.Config;

public class GrassFactory implements EntityFactory {

    @Override
    public Entity create(MapOfWorld map, Config config) {
        return new Grass();
    }
}
