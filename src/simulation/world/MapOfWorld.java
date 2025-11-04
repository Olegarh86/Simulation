package simulation.world;

import simulation.entity.Entity;
import simulation.entity.creatures.Creature;
import simulation.config.Config;

import java.util.*;

public class MapOfWorld {
    private final static String ERROR_COORDINATE_NOT_VALID = "This coordinate is off the world";
    private final Config config;
    private final Map<Coordinate, Entity> coordinatesEntities = new HashMap<>();

    public MapOfWorld(Config config) {
        this.config = config;
    }

    public Map<Coordinate, Entity> getCoordinatesEntities() {
        return new HashMap<>(coordinatesEntities);
    }

    public boolean coordinateIsValid(Coordinate coordinate) {
        return coordinate.line() >= 0 && coordinate.line() < config.numberOfColumns &&
                coordinate.column() >= 0 && coordinate.column() + 1 <= config.numberOfLines;
    }

    public void setEntity(Coordinate coordinate, Entity entity) {
        if(coordinateIsValid(coordinate)) {
            coordinatesEntities.put(coordinate, entity);
        } else {
            throw new RuntimeException(ERROR_COORDINATE_NOT_VALID);
        }
    }

    public Optional<Entity> getEntity(Coordinate coordinate) {
        if(coordinateIsValid(coordinate)) {
            return Optional.ofNullable(coordinatesEntities.get(coordinate));
        } else {
            throw new RuntimeException(ERROR_COORDINATE_NOT_VALID);
        }
    }

    public void deleteEntity(Coordinate coordinate) {
        if(coordinateIsValid(coordinate)) {
            coordinatesEntities.remove(coordinate);
        } else {
            throw new RuntimeException(ERROR_COORDINATE_NOT_VALID);
        }
    }

    public void moveCreature(Creature currentCreature, Coordinate startCoordinate, Coordinate newCoordinate) {
        if(coordinateIsValid(startCoordinate) && coordinateIsValid(newCoordinate)) {
            coordinatesEntities.put(newCoordinate, currentCreature);
            coordinatesEntities.remove(startCoordinate);
        } else {
            throw new RuntimeException(ERROR_COORDINATE_NOT_VALID);
        }
    }
}
