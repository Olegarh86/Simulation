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
    public List<Coordinate> findWayToTarget(MapOfWorld map, Creature creature, Coordinate startCoordinate) {
        Queue<Coordinate> queue = new LinkedList<>();
        Map<Coordinate, Coordinate> path = new HashMap<>();
        Set<Coordinate> visited = new HashSet<>();
        List<Coordinate> wayToTarget = new ArrayList<>();
        List<Coordinate> wayToTargetAlt = new ArrayList<>();

        queue.add(startCoordinate);
        path.put(startCoordinate, null);
        visited.add(startCoordinate);
        List<Nodes> arr = new ArrayList<>();

        while (!queue.isEmpty()) {
            Coordinate tempCoordinate = queue.poll();
            Nodes temp = new Nodes();
            temp.add(tempCoordinate);
            if (!map.getEntity(tempCoordinate).getName().equals(creature.getTarget())) {
                List<Coordinate> cellsAvailableToMove = findAllCellsAvailableForMovement(map, creature, tempCoordinate);

                for (Coordinate nextCoordinate : cellsAvailableToMove) {

                    if (!visited.contains(nextCoordinate)) {
                        queue.add(nextCoordinate);
                        path.put(nextCoordinate, tempCoordinate);
                        temp.add(nextCoordinate);
//                        start.add(nextCoordinate);
                        visited.add(nextCoordinate);
                    }
                }
                arr.add(temp);
            } else {
                wayToTargetAlt.add(tempCoordinate);
                while (tempCoordinate != startCoordinate) {
//                    wayToTarget.add(tempCoordinate);
                    for (Nodes node : arr) {
                        Nodes.Node temp1 = node.head;

                        while (temp1.coordinate != null) {
                            if (temp1.coordinate.equals(tempCoordinate)) {
                                wayToTargetAlt.add(node.head.coordinate);
                                tempCoordinate = node.head.coordinate;
                                break;
                            }
                            if (temp1.next == null) {
                                break;
                            }
                            temp1 = temp1.next;
                        }
                    }
//                    tempCoordinate = path.remove(tempCoordinate);
                }
                Collections.reverse(wayToTargetAlt);
                return wayToTargetAlt;
            }
        }
        Collections.reverse(wayToTargetAlt);
        return wayToTargetAlt;
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
    public Node head;
    public Node end;

    class Node {
        public Coordinate coordinate;
        public Node prev;
        public Node next;

        public Node(Coordinate coordinate, Node prev, Node next) {
            this.coordinate = coordinate;
            this.prev = prev;
            this.next = next;
        }

        public Node getNode(Coordinate coordinate) {
            return null;
        }

        public Coordinate getCoordinate(Node node) {
            return node.coordinate;
        }
    }

    public void add(Coordinate coordinate) {
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
