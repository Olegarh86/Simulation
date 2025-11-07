package simulation.world.pathFinder;

import simulation.world.Coordinate;

public class Nodes {
    private Node head;

    protected Node getHead() {
        return this.head;
    }

    protected void addNode(Coordinate coordinate) {
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
    static class Node {

        private final Coordinate coordinate;
        private Node next;

        protected Node(Coordinate coordinate, Node prev, Node next) {
            this.coordinate = coordinate;
            this.next = next;
        }

        protected Node getNextNode() {
            return this.next;
        }

        protected Coordinate getCoordinate() {
            return this.coordinate;
        }

    }
}
