package simulation.map;

import simulation.entity.Creature;
import simulation.utils.Config;

import java.util.*;

import static simulation.map.Coordinate.getCoordinate;

public class BFSPathFinder implements PathFinder {
    Config config;
    public BFSPathFinder(Config config) {
        this.config = config;
    }

    @Override
    public ArrayList<Coordinate> findAllCellsAvailableForMovement(MapOfWorld map, Creature creature, Coordinate startCoordinate) {
        ArrayList<Coordinate> allCellsAvailableForMove = new ArrayList<>();
        Coordinate tempCoordinate = getCoordinate(startCoordinate.getLine() - 1, startCoordinate.getColumn());
        if (tempCoordinate.getLine() >= 0 && creature.cellAvailableToMove(map, tempCoordinate)) {
            allCellsAvailableForMove.add(tempCoordinate);
        }

        tempCoordinate = getCoordinate(startCoordinate.getLine() + 1, startCoordinate.getColumn());
        if (tempCoordinate.getLine() < config.numberOfColumns && creature.cellAvailableToMove(map, tempCoordinate)) {
            allCellsAvailableForMove.add(tempCoordinate);
        }

        tempCoordinate = getCoordinate(startCoordinate.getLine(), startCoordinate.getColumn() - 1);
        if (tempCoordinate.getColumn() >= 0 && creature.cellAvailableToMove(map, tempCoordinate)) {
            allCellsAvailableForMove.add(tempCoordinate);
        }

        tempCoordinate = getCoordinate(startCoordinate.getLine(), startCoordinate.getColumn() + 1);
        if (tempCoordinate.getColumn() + 1 <= config.numberOfLines && creature.cellAvailableToMove(map, tempCoordinate)) {
            allCellsAvailableForMove.add(tempCoordinate);
        }

        return allCellsAvailableForMove;
    }

    @Override
    public ArrayList<Coordinate> findWayToTarget(MapOfWorld map, Creature creature, Coordinate startCoordinate) {
        Queue<Coordinate> queue = new LinkedList<>();
        Map<Coordinate, Coordinate> path = new HashMap<>();
        Set<Coordinate> visited = new HashSet<>();
        ArrayList<Coordinate> wayToTarget = new ArrayList<>();

        queue.add(startCoordinate);
        path.put(startCoordinate, null);
        visited.add(startCoordinate);

        while (!queue.isEmpty()) {
            Coordinate tempCoordinate = queue.poll();

            if (!map.biMap.get(tempCoordinate).getName().equals(creature.getTarget())) {
                ArrayList<Coordinate> cellsAvailableToMove = findAllCellsAvailableForMovement(map, creature, tempCoordinate);

                for (Coordinate nextCoordinate : cellsAvailableToMove) {

                    if (!visited.contains(nextCoordinate)) {
                        queue.add(nextCoordinate);
                        path.put(nextCoordinate, tempCoordinate);
                        visited.add(nextCoordinate);
                    }
                }
            } else {
                while (tempCoordinate != null) {

                    wayToTarget.add(tempCoordinate);
                    tempCoordinate = path.remove(tempCoordinate);
                }
                return wayToTarget;
            }
        }
        return wayToTarget;
    }
}
