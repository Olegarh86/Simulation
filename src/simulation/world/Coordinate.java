package simulation.world;

import simulation.utils.config.Config;

import java.util.*;

public record Coordinate(int line, int column) {

    public static Coordinate getCoordinate(int line, int column) {
        return new Coordinate(line, column);
    }

    public static Coordinate getRandomCoordinate(Config config) {
        Random random = new Random();
        int randomString = random.nextInt(config.numberOfColumns);
        int randomColumn = random.nextInt(config.numberOfLines);
        return getCoordinate(randomString, randomColumn);
    }
}
