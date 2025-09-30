package simulation.map;

import simulation.Simulation;
import simulation.entity.Creature;

import java.util.*;

public class Coordinate implements Comparable<Coordinate> {
    public final int string;
    public final int column;

    public Coordinate(int string, int column) {
        this.string = string;
        this.column = column;
    }

    public static Coordinate getRandomCoordinate(int strings, int columns) {
        int randomString = Simulation.RANDOM.nextInt(strings) + 1;
        int randomColumn = Simulation.RANDOM.nextInt(columns) + 1;
        return new Coordinate(randomString, randomColumn);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Coordinate that = (Coordinate) o;
        return string == that.string && column == that.column;
    }

    @Override
    public int hashCode() {
        return Objects.hash(string, column);
    }


    @Override
    public int compareTo(Coordinate coordinate) {
        if ((this.string * 10) + this.column > (coordinate.string * 10) + coordinate.column) {
            return 1;
        } else if ((this.string * 10) + this.column < (coordinate.string * 10) + coordinate.column) {
            return -1;
        }
        return 0;
    }
}
