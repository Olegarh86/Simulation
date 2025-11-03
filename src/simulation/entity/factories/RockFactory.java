package simulation.entity.factories;

import simulation.entity.Entity;
import simulation.entity.Rock;
import simulation.world.MapOfWorld;
import simulation.config.Config;

public class RockFactory implements EntityFactory {

    @Override
    public Entity create(MapOfWorld map, Config config) {
        return new Rock();
    }
}
