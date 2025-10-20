package simulation.entity;

import simulation.utils.Config;

import java.util.HashSet;
import java.util.Set;

public class CreatorOfEmptyCells implements Creator {

    @Override
    public Set<Entity> createMultipleEntities(Config config) {
        return new HashSet<>();
    }

    @Override
    public Entity createEntity(Config config) {
        return new EmptyCell();
    }
}

