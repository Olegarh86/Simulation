package simulation.entity.factories;

import simulation.entity.Entity;
import simulation.entity.creatures.Predator;
import simulation.world.MapOfWorld;
import simulation.utils.config.Config;

import java.util.HashSet;
import java.util.Set;

public class PredatorFactory implements EntityFactory {

    @Override
    public Set<Entity> createMultipleEntities(MapOfWorld world, Config config) {
        Set<Entity> entities = new HashSet<>();
        for (int i = 0; i < config.numberOfPredators; i++) {
            entities.add(new Predator(config.predatorsSpeed, config.predatorsHp, config.predatorsAttackPower));
        }
        return entities;
    }
}