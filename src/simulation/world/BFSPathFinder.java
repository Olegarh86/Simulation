package simulation.world;

import simulation.entity.EmptyCell;
import simulation.entity.Entity;
import simulation.entity.creatures.Creature;
import simulation.utils.config.Config;

import java.util.*;

import static simulation.world.Coordinate.getCoordinate;

public class BFSPathFinder implements PathFinder {
    private final Config config;

    public BFSPathFinder(Config config) {
        this.config = config;
    }


    @Override
    public Coordinate findCellForMove(MapOfWorld map, Creature creature, Coordinate startCoordinate) {
        List<Coordinate> wayToTarget = findWayToTarget(map, creature, startCoordinate);

        return selectCoordinateForMoveWithCreatureSpeed(creature, wayToTarget, startCoordinate);
    }

    private List<Coordinate> findWayToTarget(MapOfWorld map, Creature creature, Coordinate startCoordinate) {
        List<Nodes> allNodes = new ArrayList<>();
        Coordinate targetCoordinate = findTarget(map, creature, startCoordinate, allNodes);
        if (targetIsAvailable(startCoordinate, targetCoordinate)) {
            return routeConstruction(startCoordinate, targetCoordinate, allNodes);
        }
        return List.of();
    }

    private Coordinate findTarget(MapOfWorld map, Creature creature, Coordinate startCoordinate, List<Nodes> allNodes) {
        Queue<Coordinate> queue = new LinkedList<>();
        Set<Coordinate> visited = new HashSet<>();
        Coordinate tempCoordinate = startCoordinate;
        visited.add(tempCoordinate);
        queue.add(tempCoordinate);

        while (!queue.isEmpty()) {
            tempCoordinate = queue.poll();
            Nodes nodes = new Nodes();
            allNodes.add(nodes);
            nodes.addNode(tempCoordinate);

            if (map.getEntity(tempCoordinate).isEmpty() || !creature.getTarget().equals(map.getEntity(tempCoordinate).get().getClass())) {
                List<Coordinate> cellsAvailableToMove = findAllCellsAvailableForMovement(map, creature, tempCoordinate);

                for (Coordinate nextCoordinate : cellsAvailableToMove) {

                    if (!visited.contains(nextCoordinate)) {
                        queue.add(nextCoordinate);
                        nodes.addNode(nextCoordinate);
                        visited.add(nextCoordinate);
                    }
                }
            } else {
                return tempCoordinate;
            }
        }
        return tempCoordinate;
    }

    private List<Coordinate> findAllCellsAvailableForMovement(MapOfWorld world, Creature creature, Coordinate startCoordinate) {
        List<Coordinate> allCellsAvailableForMove = new ArrayList<>();
        for (Coordinate coordinate : findAllShifts(startCoordinate)) {
            if (isValidCoordinate(coordinate) && cellIsAvailableForMove(world, creature, coordinate)) {
                allCellsAvailableForMove.add(coordinate);
            }
        }
        return allCellsAvailableForMove;
    }

    private boolean cellIsAvailableForMove(MapOfWorld world, Creature creature, Coordinate tempCoordinate) {
        Optional<Entity> cellForMove = world.getEntity(tempCoordinate);
        return cellForMove.map(entity -> !creature.GetObstacles().contains(entity.getClass())).orElse(true);
    }

    private boolean isValidCoordinate(Coordinate tempCoordinate) {
        return tempCoordinate.line() >= 0 && tempCoordinate.line() < config.numberOfColumns &&
                tempCoordinate.column() >= 0 && tempCoordinate.column() + 1 <= config.numberOfLines;
    }

    private List<Coordinate> findAllShifts(Coordinate startCoordinate) {
        List<Coordinate> allShifts = new ArrayList<>();
        allShifts.add(getCoordinate(startCoordinate.line() - 1, startCoordinate.column()));
        allShifts.add(getCoordinate(startCoordinate.line() + 1, startCoordinate.column()));
        allShifts.add(getCoordinate(startCoordinate.line(), startCoordinate.column() - 1));
        allShifts.add(getCoordinate(startCoordinate.line(), startCoordinate.column() + 1));
        return allShifts;
    }

    private List<Coordinate> routeConstruction(Coordinate startCoordinate, Coordinate tempCoordinate, List<Nodes> allNodes) {
        List<Coordinate> wayToTarget = new ArrayList<>();
        wayToTarget.add(tempCoordinate);
        while (tempCoordinate != startCoordinate) {

            for(Nodes currentNodes : allNodes) {
                Nodes.Node node = currentNodes.getHead();

                while (node.getCoordinate() != null) {

                    if (node.getCoordinate().equals(tempCoordinate)) {
                        wayToTarget.add(currentNodes.getHead().getCoordinate());
                        tempCoordinate = currentNodes.getHead().getCoordinate();
                        break;
                    }
                    node = node.getNextNode();

                    if (node == null) {
                        break;
                    }
                }
            }
        }
        Collections.reverse(wayToTarget);
        return wayToTarget;
    }

    private boolean targetIsAvailable(Coordinate startCoordinate, Coordinate targetCoordinate) {
        return !targetCoordinate.equals(startCoordinate);
    }

    private Coordinate selectCoordinateForMoveWithCreatureSpeed(Creature currentCreature, List<Coordinate> wayToTarget, Coordinate startCoordinate) {
        if (wayToTarget.isEmpty()) {
            return startCoordinate;
        }
        if (wayToTarget.size() > currentCreature.getSpeed()) {
            return wayToTarget.get(currentCreature.getSpeed());
        }
        return wayToTarget.get(wayToTarget.size() - 1);
    }
}
