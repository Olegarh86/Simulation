package simulation.world;

import simulation.entity.*;
import simulation.entity.Entity;
import simulation.entity.creatures.Creature;
import simulation.entity.factories.EntityFactory;
import simulation.utils.config.Config;

import java.util.*;

import static simulation.world.Coordinate.getCoordinate;

public class MapOfWorld {

    private final Map<Coordinate, Entity> coordinatesEntities = new TreeMap<>();
    private Map<Entity, Coordinate> creaturesCoordinates = new TreeMap<>();

    public MapOfWorld(Config config) {
        for (int line = 0; line < config.numberOfColumns; line++) {
            for (int column = 0; column < config.numberOfLines; column++) {
                Coordinate tempCoordinate = getCoordinate(line, column);
                setEntity(tempCoordinate, new EmptyCell());
            }
        }
    }

    public Map<Coordinate, Entity> getCoordinatesEntities() {
        return coordinatesEntities;
    }

    public Map<Entity, Coordinate> getCreaturesCoordinates() {
        return creaturesCoordinates;
    }

    public void clearCreaturesCoordinates() {
        creaturesCoordinates.clear();
    }

    public void setEntity(Coordinate coordinate, Entity entity) {
        if (entity.isMovable()) {
            coordinatesEntities.put(coordinate, entity);
            creaturesCoordinates.put(entity, coordinate);
        }
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

    public static void setEntitiesToRandomCoordinate(MapOfWorld world, Config config, EntityFactory entityFactory) {
        Set<Entity> entities = entityFactory.createMultipleEntities(world, config);
        Coordinate randomCoordinate;
        for (Entity entity : entities) {
            randomCoordinate = Coordinate.chooseEmptyRandomCoordinate(world, config);
            world.setEntity(randomCoordinate, entity);
            EmptyCell.emptyCellsCount--;
        }
    }
}
