package simulation.map;

import simulation.entity.Creature;

import java.util.ArrayList;

public interface PathFinder {
    ArrayList<Coordinate>  findAllCellsAvailableForMovement(MapOfWorld map, Creature creature, Coordinate startCoordinate);
    ArrayList<Coordinate>  findWayToTarget(MapOfWorld map, Creature creature, Coordinate startCoordinate);
}
