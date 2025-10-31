package simulation.world;

import simulation.entity.creatures.Creature;

public interface PathFinder {
    Coordinate findCellForMove(MapOfWorld map, Creature creature, Coordinate startCoordinate);
}
