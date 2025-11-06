package simulation.world.pathFinder;

import simulation.entity.Entity;
import simulation.world.Coordinate;
import simulation.world.WorldMap;

import java.util.List;

public interface PathFinder {
    List<Coordinate> find(WorldMap map, Class<? extends Entity> target,
                          List<Class<? extends Entity>> obstacles, Coordinate startCoordinate);
}
