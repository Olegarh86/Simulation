package simulation.entity.entityFactories;

import simulation.entity.Entity;
import simulation.entity.creatures.Predator;
import simulation.config.Config;

public class PredatorFactory implements EntityFactory {

    @Override
    public Entity create(Config config) {
        return new Predator(config.predatorsSpeed, config.predatorsHp, config.predatorsAttackPower);
    }
}