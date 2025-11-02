package simulation.entity.factories;

import simulation.entity.Entity;
import simulation.entity.Grass;
import simulation.world.MapOfWorld;
import simulation.utils.config.Config;

import java.util.HashSet;
import java.util.Set;

public class GrassFactory implements EntityFactory {

    @Override
    public Entity create(MapOfWorld map, Config config) {
        return new Grass();
    }
}
