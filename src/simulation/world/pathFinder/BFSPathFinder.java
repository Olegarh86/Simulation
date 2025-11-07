package simulation.world.pathFinder;

import simulation.entity.Entity;
import simulation.world.Coordinate;
import simulation.world.WorldMap;

import java.util.*;

public class BFSPathFinder implements PathFinder {

    @Override
    public List<Coordinate> find(WorldMap map,
                                 Class<? extends Entity> target,
                                 Coordinate startCoordinate) {
        List<Nodes> allNodes = new ArrayList<>();
        Coordinate targetCoordinate = findTarget(map, target, startCoordinate, allNodes);
        if (isTargetAvailable(startCoordinate, targetCoordinate)) {
            return routeConstruction(startCoordinate, targetCoordinate, allNodes);
        }
        return List.of();
    }

    private Coordinate findTarget(WorldMap map,
                                  Class<? extends Entity> target,
                                  Coordinate startCoordinate,
                                  List<Nodes> allNodes) {
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

            if (map.getEntity(tempCoordinate).isEmpty() || !target.equals(map.getEntity(tempCoordinate).get().getClass())) {
                List<Coordinate> cellsAvailableForMove = findAllCellsAvailableForMovement(target, map, tempCoordinate);

                for (Coordinate nextCoordinate : cellsAvailableForMove) {

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

    private List<Coordinate> findAllCellsAvailableForMovement(Class<? extends Entity> target, WorldMap world,
                                                              Coordinate startCoordinate) {
        List<Coordinate> allCellsAvailableForMove = new ArrayList<>();
        for (Coordinate coordinate : findAllShifts(startCoordinate)) {
            if (world.isValidCoordinate(coordinate) && isCellAvailableForMove(target, world, coordinate)) {
                allCellsAvailableForMove.add(coordinate);
            }
        }
        return allCellsAvailableForMove;
    }

    private boolean isCellAvailableForMove(Class<? extends Entity> target, WorldMap world, Coordinate tempCoordinate) {
        Optional<Entity> cellForMove = world.getEntity(tempCoordinate);
        return cellForMove.map(entity -> entity.getClass().equals(target)).orElse(true);
    }

    private List<Coordinate> findAllShifts(Coordinate startCoordinate) {
        List<Coordinate> allShifts = new ArrayList<>();
        allShifts.add(new Coordinate(startCoordinate.row() - 1, startCoordinate.column()));
        allShifts.add(new Coordinate(startCoordinate.row() + 1, startCoordinate.column()));
        allShifts.add(new Coordinate(startCoordinate.row(), startCoordinate.column() - 1));
        allShifts.add(new Coordinate(startCoordinate.row(), startCoordinate.column() + 1));
        return allShifts;
    }

    private boolean isTargetAvailable(Coordinate startCoordinate, Coordinate targetCoordinate) {
        return !targetCoordinate.equals(startCoordinate);
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
}
