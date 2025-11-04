package simulation.entity.entityFactories;

import simulation.entity.Entity;
import simulation.entity.creatures.Herbivore;
import simulation.config.Config;

public class HerbivoreFactory implements EntityFactory {

    @Override
    public Entity create(Config config) {
        return new Herbivore(config.herbivoresSpeed, config.herbivoresHp);
    }
}
