package simulation.world;

import simulation.entity.Entity;
import simulation.entity.creatures.Creature;

import java.util.List;

public interface PathFinder {
    Coordinate findCellForMove(MapOfWorld map, Creature creature, Class<? extends Entity> target,
                               List<Class<? extends Entity>> obstacles, Coordinate startCoordinate);
}
