package simulation.entity;

import simulation.map.MapOfWorld;
import simulation.utils.config.Config;

import java.util.HashSet;
import java.util.Set;

public class CreatorOfHerbivores implements Creator {

    @Override
    public Set<Entity> createMultipleEntities(MapOfWorld map, Config config) {
        Set<Entity> entities = new HashSet<>();
        for (int i = 0; i < config.numberOfHerbivores; i++) {
            entities.add(new Herbivore(config.herbivoresSpeed, config.herbivoresHp));
        }
        return entities;
    }
}
