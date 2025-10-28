package simulation.world;

import simulation.entity.creatures.Creature;

import java.util.List;

public interface PathFinder {
    Coordinate findCellForMove(MapOfWorld map, Creature creature, Coordinate startCoordinate);
    List<Coordinate>  findWayToTarget(MapOfWorld map, Creature creature, Coordinate startCoordinate);
    List<Coordinate> findAllCellsAvailableForMovement(MapOfWorld map, Creature creature, Coordinate startCoordinate);
}
