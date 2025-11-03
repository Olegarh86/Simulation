package simulation.world;

import simulation.entity.Entity;
import simulation.entity.creatures.Creature;
import simulation.utils.config.Config;

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

    public void coordinateValidation(Coordinate coordinate) { //TODO new class and delete config from this class
        if (coordinate.line() < config.numberOfColumns && coordinate.column() < config.numberOfLines) {
            return;
        }
        throw new RuntimeException(ERROR_COORDINATE_NOT_VALID);
    }

    public void setEntity(Coordinate coordinate, Entity entity) {
        coordinateValidation(coordinate);
        coordinatesEntities.put(coordinate, entity);
    }

    public Optional<Entity> getEntity(Coordinate coordinate) {
        coordinateValidation(coordinate);
        return Optional.ofNullable(coordinatesEntities.get(coordinate));
    }

    public void deleteEntity(Coordinate coordinate) {
        coordinateValidation(coordinate);
        coordinatesEntities.remove(coordinate);
    }

    public void moveCreature(Creature currentCreature, Coordinate startCoordinate, Coordinate newCoordinate) {
        coordinateValidation(startCoordinate);
        coordinateValidation(newCoordinate);
        coordinatesEntities.put(newCoordinate, currentCreature);
        coordinatesEntities.remove(startCoordinate);
    }
}
