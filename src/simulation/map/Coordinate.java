package simulation.map;

import simulation.utils.Config;

import java.util.*;

public class Coordinate implements Comparable<Coordinate> {
    private final int line;
    private final int column;

    private Coordinate(int line, int column) {
        this.line = line;
        this.column = column;
    }

    public static Coordinate getCoordinate(int line, int column) {
        return new Coordinate(line, column);
    }

    public int getLine() {
        return line;
    }

    public int getColumn() {
        return column;
    }

    public static Coordinate getRandomCoordinate(Config config) {
        Random random = new Random();
        int randomString = random.nextInt(config.numberOfColumns);
        int randomColumn = random.nextInt(config.numberOfLines);
        return new Coordinate(randomString, randomColumn);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Coordinate that = (Coordinate) o;
        return line == that.line && column == that.column;
    }

    @Override
    public int hashCode() {
        return Objects.hash(line, column);
    }


    @Override
    public int compareTo(Coordinate coordinate) {
        if ((this.line * 10) + this.column > (coordinate.line * 10) + coordinate.column) {
            return 1;
        } else if ((this.line * 10) + this.column < (coordinate.line * 10) + coordinate.column) {
            return -1;
        }
        return 0;
    }
}
