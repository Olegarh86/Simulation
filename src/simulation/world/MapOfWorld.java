package simulation.world;

import simulation.entity.*;
import simulation.entity.Entity;
import simulation.entity.creatures.Creature;
import simulation.utils.config.Config;

import java.util.*;

import static simulation.world.Coordinate.getCoordinate;

public class MapOfWorld {
    private final Map<Coordinate, Entity> coordinatesEntities = new TreeMap<>();

    public MapOfWorld(Config config) {
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

    public void setEntity(Coordinate coordinate, Entity entity) {
        coordinatesEntities.put(coordinate, entity);
    }

    public Entity getEntity(Coordinate coordinate) {
        return coordinatesEntities.get(coordinate);
    }

    public void deleteEntity(Coordinate coordinate) {
        Entity entity = getEntity(coordinate);
        entity.decrementCountOfEntity();
        coordinatesEntities.put(coordinate, new EmptyCell());
    }

    public void moveCreatureToEmptyCell(Creature currentCreature, Coordinate startCoordinate, Coordinate newCoordinate) {
        Entity emptyCell = coordinatesEntities.replace(newCoordinate, currentCreature);
        if (emptyCell != null) {
            setEntity(startCoordinate, emptyCell);
        }
    }
}
