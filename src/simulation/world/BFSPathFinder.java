package simulation.world;

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
    public List<Coordinate> findWayToTarget(MapOfWorld map, Creature creature, Coordinate startCoordinate) {
        List<Nodes> allNodes = new ArrayList<>();
        Coordinate targetCoordinate = findTarget(map, creature, startCoordinate, allNodes);
        if (targetIsAvailable(startCoordinate, targetCoordinate)) {
            return routeConstruction(startCoordinate, targetCoordinate, allNodes);
        }
        return List.of();
    }

    @Override
    public Coordinate findTarget(MapOfWorld map, Creature creature, Coordinate startCoordinate, List<Nodes> allNodes) {
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

            if (!map.getEntity(tempCoordinate).getName().equals(creature.getTarget())) {
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

    @Override
    public Coordinate findCellForMove(MapOfWorld map, Creature creature, Coordinate startCoordinate) {
        List<Coordinate> wayToTarget = findWayToTarget(map, creature, startCoordinate);

        return selectCoordinateForMoveWithCreatureSpeed(creature, wayToTarget, startCoordinate);
    }

    @Override
    public List<Coordinate> findAllCellsAvailableForMovement(MapOfWorld world, Creature creature, Coordinate startCoordinate) {
        List<Coordinate> allCellsAvailableForMove = new ArrayList<>();
        for (Coordinate coordinate : findAllShifts(startCoordinate)) {
            if (isValid(coordinate) && cellIsAvailableForMove(world, creature, coordinate)) {
                allCellsAvailableForMove.add(coordinate);
            }
        }
        return allCellsAvailableForMove;
    }

    @Override
    public boolean cellIsAvailableForMove(MapOfWorld world, Creature creature, Coordinate tempCoordinate) {
        String cellForMove = world.getEntity(tempCoordinate).getName();
        return !creature.GetObstacles().contains(cellForMove);
    }

    @Override
    public boolean isValid(Coordinate tempCoordinate) {
        return tempCoordinate.getLine() >= 0 && tempCoordinate.getLine() < config.numberOfColumns &&
                tempCoordinate.getColumn() >= 0 && tempCoordinate.getColumn() + 1 <= config.numberOfLines;
    }

    @Override
    public List<Coordinate> findAllShifts(Coordinate startCoordinate) {
        List<Coordinate> allShifts = new ArrayList<>();
        allShifts.add(getCoordinate(startCoordinate.getLine() - 1, startCoordinate.getColumn()));
        allShifts.add(getCoordinate(startCoordinate.getLine() + 1, startCoordinate.getColumn()));
        allShifts.add(getCoordinate(startCoordinate.getLine(), startCoordinate.getColumn() - 1));
        allShifts.add(getCoordinate(startCoordinate.getLine(), startCoordinate.getColumn() + 1));
        return allShifts;
    }

    @Override
    public List<Coordinate> routeConstruction(Coordinate startCoordinate, Coordinate tempCoordinate, List<Nodes> allNodes) {
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

    @Override
    public boolean targetIsAvailable(Coordinate startCoordinate, Coordinate targetCoordinate) {
        return !targetCoordinate.equals(startCoordinate);
    }

    @Override
    public Coordinate selectCoordinateForMoveWithCreatureSpeed(Creature currentCreature, List<Coordinate> wayToTarget, Coordinate startCoordinate) {
        if (wayToTarget.isEmpty()) {
            return startCoordinate;
        }
        if (wayToTarget.size() > currentCreature.getSpeed()) {
            return wayToTarget.get(currentCreature.getSpeed());
        }
        return wayToTarget.get(wayToTarget.size() - 1);
    }
}
