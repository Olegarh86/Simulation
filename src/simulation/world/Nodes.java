package simulation.world;

public class Nodes {
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