package simulation.entity.factories;

import simulation.entity.Entity;
import simulation.entity.creatures.Predator;
import simulation.world.MapOfWorld;
import simulation.utils.config.Config;

public class PredatorFactory implements EntityFactory {

    @Override
    public Entity create(MapOfWorld map, Config config) {
        return new Predator(config.predatorsSpeed, config.predatorsHp, config.predatorsAttackPower);
    }
}