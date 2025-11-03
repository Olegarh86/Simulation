package simulation.world.pathFinder;

import simulation.entity.Entity;
import simulation.world.Coordinate;
import simulation.world.MapOfWorld;

import java.util.List;

public interface PathFinder {
    List<Coordinate> findWayToTarget(MapOfWorld map, Class<? extends Entity> target,
                                     List<Class<? extends Entity>> obstacles, Coordinate startCoordinate);
}
