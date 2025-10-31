package simulation.world;

import simulation.entity.EmptyCell;
import simulation.utils.config.Config;

import java.util.*;

public record Coordinate(int line, int column) implements Comparable<Coordinate> {

    public static Coordinate getCoordinate(int line, int column) {
        return new Coordinate(line, column);
    }

    private static Coordinate getRandomCoordinate(Config config) {
        Random random = new Random();
        int randomString = random.nextInt(config.numberOfColumns);
        int randomColumn = random.nextInt(config.numberOfLines);
        return new Coordinate(randomString, randomColumn);
    }

    public static Coordinate chooseEmptyRandomCoordinate(MapOfWorld world, Config config) {
        Coordinate randomCoordinate = getRandomCoordinate(config);

        while (!(world.getEntity(randomCoordinate).getName().equals("EmptyCell"))) {
            randomCoordinate = getRandomCoordinate(config);

            if (EmptyCell.emptyCellsCount < Math.max(config.numberOfGrasses,
                    Math.max(config.numberOfHerbivores, config.numberOfPredators))) {
                throw new RuntimeException("Empty cells is not available, too much entities in the simulation.");
            }
        }
        return randomCoordinate;
    }

    @Override
    public int compareTo(Coordinate coordinate) {
        if (this.line != coordinate.line) {
            return Integer.compare(this.line, coordinate.line);
        }
        return Integer.compare(this.column, coordinate.column);
    }
}
