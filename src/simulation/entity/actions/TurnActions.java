package simulation.entity.actions;

import simulation.entity.creatures.Creature;
import simulation.entity.Entity;
import simulation.world.Coordinate;
import simulation.world.MapOfWorld;
import simulation.world.PathFinder;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class TurnActions implements Actions {
    private final MapOfWorld world;
    private final PathFinder pathFinder;


    public TurnActions(MapOfWorld world, PathFinder pathFinder) {
        this.world = world;
        this.pathFinder = pathFinder;
    }

    @Override
    public void execute() {
        Map<Entity, Coordinate> creaturesCoordinates = new TreeMap<>();
        Map<Entity, Coordinate> newCreaturesCoordinates = new TreeMap<>();
        for (Map.Entry<Coordinate, Entity> entry : world.getCoordinatesEntities().entrySet()) {
            if (entry.getValue().isMovable()) {
                creaturesCoordinates.put(entry.getValue(), entry.getKey());
            }
        }
        allCreaturesMove(creaturesCoordinates, newCreaturesCoordinates);
    }

    private void allCreaturesMove(Map<Entity, Coordinate> creaturesCoordinates, Map<Entity, Coordinate> newCreaturesCoordinates) {
        for (Map.Entry<Entity, Coordinate> entry : creaturesCoordinates.entrySet()) {
            Coordinate startCoordinate = entry.getValue();
            Creature creature = (Creature) entry.getKey();
            creature.makeMove(world, creature, startCoordinate, pathFinder, newCreaturesCoordinates);
        }
    }
}
