package simulation.world;

import simulation.entity.Entity;

import java.util.List;

public interface PathFinder {
    List<Coordinate> findWayToTarget(MapOfWorld map, Class<? extends Entity> target,
                                     List<Class<? extends Entity>> obstacles, Coordinate startCoordinate);
}
