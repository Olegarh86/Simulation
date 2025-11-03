package simulation.entity.factories;

import simulation.entity.Entity;
import simulation.entity.creatures.Herbivore;
import simulation.world.MapOfWorld;
import simulation.config.Config;

public class HerbivoreFactory implements EntityFactory {

    @Override
    public Entity create(MapOfWorld map, Config config) {
        return new Herbivore(config.herbivoresSpeed, config.herbivoresHp);
    }
}
