package simulation.world;

import simulation.entity.Entity;

import java.util.*;

public class WorldMap {
    private final static String ERROR_COORDINATE_NOT_VALID = "This coordinate is off the world";
    private final static String ERROR_CREATURE_ABSENT = "There is no creature at these coordinates";
    private final int gameMapHeight;
    private final int gameMapWidth;
    private final Map<Coordinate, Entity> coordinatesEntities = new HashMap<>();

    public WorldMap(int gameMapHeight, int  gameMapWidth) {
        this.gameMapHeight = gameMapHeight;
        this.gameMapWidth = gameMapWidth;
    }

    public Map<Coordinate, Entity> getCoordinatesEntities() {
        return new HashMap<>(coordinatesEntities);
    }

    public boolean isValidCoordinate(Coordinate coordinate) {
        return coordinate.row() >= 0 && coordinate.row() < gameMapHeight &&
                coordinate.column() >= 0 && coordinate.column() + 1 <= gameMapWidth;
    }

    public void setEntity(Coordinate coordinate, Entity entity) {
        if(!isValidCoordinate(coordinate)) {
            throw new RuntimeException(ERROR_COORDINATE_NOT_VALID);
        }
        coordinatesEntities.put(coordinate, entity);
    }

    public Optional<Entity> getEntity(Coordinate coordinate) {
        if(!isValidCoordinate(coordinate)) {
            throw new RuntimeException(ERROR_COORDINATE_NOT_VALID);
        }
        return Optional.ofNullable(coordinatesEntities.get(coordinate));
    }

    public void deleteEntity(Coordinate coordinate) {
        if(!isValidCoordinate(coordinate)) {
            throw new RuntimeException(ERROR_COORDINATE_NOT_VALID);
        }
        if(isEmptyCell(coordinate)) {
            throw new RuntimeException(ERROR_CREATURE_ABSENT);
        }
        coordinatesEntities.remove(coordinate);
    }

    private boolean isEmptyCell(Coordinate coordinate) {
        return getEntity(coordinate).isEmpty();
    }
}
