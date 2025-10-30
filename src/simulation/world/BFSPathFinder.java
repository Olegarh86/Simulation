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
    public Coordinate findCellForMove(MapOfWorld map, Creature creature, Coordinate startCoordinate) {
        List<Coordinate> wayToTarget = findWayToTarget(map, creature, startCoordinate);

        return selectNewCoordinateWithCreatureSpeed(creature, wayToTarget, startCoordinate);
    }

    @Override
    public Coordinate findTarget(MapOfWorld map, Creature creature, Coordinate startCoordinate) {
//        Set<Coordinate> visited = new HashSet<>();
//        visited.add(startCoordinate);
//
//        if (!map.getEntity(startCoordinate).getName().equals(creature.getTarget())) {
//            List<Coordinate> cellsAvailableToMove = findAllCellsAvailableForMovement(map, creature, startCoordinate);
//
//            for (Coordinate nextCoordinate : cellsAvailableToMove) {
//
//                if (!visited.contains(nextCoordinate)) {
//                    queue.add(nextCoordinate);
//                    nodes.addNode(nextCoordinate);
//                    visited.add(nextCoordinate);
//                }
//            }
//        }
        return null;
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
    public List<Coordinate> findWayToTarget(MapOfWorld map, Creature creature, Coordinate startCoordinate) {
        Queue<Coordinate> queue = new LinkedList<>();

        List<Nodes> allNodes = new ArrayList<>();
        Set<Coordinate> visited = new HashSet<>();
        visited.add(startCoordinate);
        queue.add(startCoordinate);

        while (!queue.isEmpty()) {
            Coordinate tempCoordinate = queue.poll();
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
                return routeConstruction(startCoordinate, tempCoordinate, allNodes);
            }
        }
        return List.of();
    }

    @Override
    public List<Coordinate> findAllCellsAvailableForMovement(MapOfWorld world, Creature creature, Coordinate startCoordinate) {
        List<Coordinate> allCellsAvailableForMove = new ArrayList<>();
        Coordinate tempCoordinate = getCoordinate(startCoordinate.getLine() - 1, startCoordinate.getColumn());
        if (tempCoordinate.getLine() >= 0 && cellAvailableForMove(world, creature, tempCoordinate)) {
            allCellsAvailableForMove.add(tempCoordinate);
        }

        tempCoordinate = getCoordinate(startCoordinate.getLine() + 1, startCoordinate.getColumn());
        if (tempCoordinate.getLine() < config.numberOfColumns && cellAvailableForMove(world, creature, tempCoordinate)) {
            allCellsAvailableForMove.add(tempCoordinate);
        }

        tempCoordinate = getCoordinate(startCoordinate.getLine(), startCoordinate.getColumn() - 1);
        if (tempCoordinate.getColumn() >= 0 && cellAvailableForMove(world, creature, tempCoordinate)) {
            allCellsAvailableForMove.add(tempCoordinate);
        }

        tempCoordinate = getCoordinate(startCoordinate.getLine(), startCoordinate.getColumn() + 1);
        if (tempCoordinate.getColumn() + 1 <= config.numberOfLines && cellAvailableForMove(world, creature, tempCoordinate)) {
            allCellsAvailableForMove.add(tempCoordinate);
        }

        return allCellsAvailableForMove;
    }

    private boolean cellAvailableForMove(MapOfWorld world, Creature creature, Coordinate tempCoordinate) {
        String cellForMove = world.getEntity(tempCoordinate).getName();
        return !creature.GetObstacles().contains(cellForMove);
    }

    public Coordinate selectNewCoordinateWithCreatureSpeed(Creature currentCreature, List<Coordinate> wayToTarget, Coordinate startCoordinate) {

        if (wayToTarget.isEmpty()) {
            return startCoordinate;
        }
        if (wayToTarget.size() > currentCreature.getSpeed()) {
            return wayToTarget.get(currentCreature.getSpeed());
        }
        return wayToTarget.get(wayToTarget.size() - 1);
    }
}

class Nodes {
    private Node head;

    static class Node {
        private final Coordinate coordinate;
        private Node next;

        public Node(Coordinate coordinate, Node prev, Node next) {
            this.coordinate = coordinate;
            this.next = next;
        }

        public Node getNextNode() {
            return this.next;
        }

        public Coordinate getCoordinate() {
            return this.coordinate;
        }
    }

    public Node getHead() {
        return this.head;
    }

    public void addNode(Coordinate coordinate) {
        if (head == null) {
            head = new Node(coordinate, null, null);
            return;
        }
        Node temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = new Node(coordinate, temp, null);
    }
}
