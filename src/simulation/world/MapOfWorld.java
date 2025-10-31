package simulation.world;

import simulation.entity.*;
import simulation.entity.Entity;
import simulation.entity.creatures.Creature;
import simulation.utils.config.Config;

import java.util.*;

import static simulation.world.Coordinate.getCoordinate;

public class MapOfWorld {
    private final Config config;
    private final Map<Coordinate, Entity> coordinatesEntities = new TreeMap<>();

    public MapOfWorld(Config config) {
        this.config = config;
        for (int line = 0; line < config.numberOfColumns; line++) {
            for (int column = 0; column < config.numberOfLines; column++) {
                Coordinate tempCoordinate = getCoordinate(line, column);
                setEntity(tempCoordinate, new EmptyCell());
            }
        }
    }

    public Map<Coordinate, Entity> getCoordinatesEntities() {
        return new TreeMap<>(coordinatesEntities);
    }

    public void coordinateValidation(Coordinate coordinate) {
        if (coordinate.line() < config.numberOfColumns && coordinate.column() < config.numberOfLines) {
            return;
        }
        throw new RuntimeException("Coordinate is not valid");
    }

    public void setEntity(Coordinate coordinate, Entity entity) {
        coordinateValidation(coordinate);
        coordinatesEntities.put(coordinate, entity);
    }

    public Entity getEntity(Coordinate coordinate) {
        coordinateValidation(coordinate);
        return coordinatesEntities.get(coordinate);
    }

    public void deleteEntity(Coordinate coordinate) {
        coordinateValidation(coordinate);
        Entity entity = getEntity(coordinate);
        entity.decrementCountOfEntity();
        coordinatesEntities.put(coordinate, new EmptyCell());
    }

    public void moveCreatureToEmptyCell(Creature currentCreature, Coordinate startCoordinate, Coordinate newCoordinate) {
        coordinateValidation(startCoordinate);
        coordinateValidation(newCoordinate);
        Entity emptyCell = coordinatesEntities.replace(newCoordinate, currentCreature);
        if (emptyCell != null) {
            setEntity(startCoordinate, emptyCell);
        }
    }
}
