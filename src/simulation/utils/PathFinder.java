package simulation.utils;

import simulation.entity.Creature;
import simulation.map.Coordinate;
import simulation.map.MapOfWorld;

import java.util.*;

public class PathFinder {
    private PathFinder() {}

    public static Queue<Coordinate> findAllCellsAvailableForMovement(MapOfWorld map, Coordinate coordinate, Creature creature) {
        Queue<Coordinate> queueAllWays = new LinkedList<>();
        Coordinate tempCoordinate = new Coordinate(coordinate.string - 1, coordinate.column);
        if (tempCoordinate.string > 0 && creature.cellAvailableToMove(map, tempCoordinate)) {
            queueAllWays.add(tempCoordinate);
        }

        tempCoordinate = new Coordinate(coordinate.string + 1, coordinate.column);
        if (tempCoordinate.string <= Config.getWeight() && creature.cellAvailableToMove(map, tempCoordinate)) {
            queueAllWays.add(tempCoordinate);
        }

        tempCoordinate = new Coordinate(coordinate.string, coordinate.column - 1);
        if (tempCoordinate.column > 0 && creature.cellAvailableToMove(map, tempCoordinate)) {
            queueAllWays.add(tempCoordinate);
        }

        tempCoordinate = new Coordinate(coordinate.string, coordinate.column + 1);
        if (tempCoordinate.column + 1 <= Config.getHeight() && creature.cellAvailableToMove(map, tempCoordinate)) {
            queueAllWays.add(tempCoordinate);
        }

        return queueAllWays;
    }

    public static ArrayList<Coordinate> findWayToTarget(MapOfWorld map, Creature creature, Coordinate startCoordinate) {
        Queue<Coordinate> queue = new LinkedList<>();
        Map<Coordinate, Coordinate> path = new HashMap<>();
        Set<Coordinate> visited = new HashSet<>();
        ArrayList<Coordinate> way = new ArrayList<>();
        queue.add(startCoordinate);
        path.put(startCoordinate, null);
        visited.add(startCoordinate);

        while (!queue.isEmpty()) {

            Coordinate tempCoordinate = queue.poll();
            if (!map.biMap.get(tempCoordinate).getName().equals(creature.getTarget())) {
                for (Coordinate coordinate1 : findAllCellsAvailableForMovement(map, tempCoordinate, creature)) {

                    if (!visited.contains(coordinate1)) {

                        queue.add(coordinate1);
                        path.put(coordinate1, tempCoordinate);
                        visited.add(coordinate1);
                    }
                }
            } else {

                while (tempCoordinate != null) {

                    way.add(tempCoordinate);
                    tempCoordinate = path.remove(tempCoordinate);

                }
                return way;
            }
        }
        return way;
    }
}
