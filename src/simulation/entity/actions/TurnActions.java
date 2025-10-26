package simulation.entity.actions;

import simulation.entity.creatures.Creature;
import simulation.entity.Entity;
import simulation.world.Coordinate;
import simulation.world.MapOfWorld;
import simulation.world.PathFinder;

import java.util.Map;

public class TurnActions implements Actions {
    private final MapOfWorld world;
    private final PathFinder pathFinder;


    public TurnActions(MapOfWorld world, PathFinder pathFinder) {
        this.world = world;
        this.pathFinder = pathFinder;
    }

    @Override
    public void execute() {
        allCreaturesMove();
        world.creaturesCoordinates.clear();
        removeDeadCreatures();
        world.newCreaturesCoordinates.clear();
    }

    private void allCreaturesMove() {
        for (Map.Entry<Entity, Coordinate> entry : world.creaturesCoordinates.entrySet()) {
            Coordinate startCoordinate = entry.getValue();
            Creature creature = (Creature) entry.getKey();
            creature.makeMove(world, creature, startCoordinate, pathFinder);
        }
    }

    private void removeDeadCreatures() {
        for (Map.Entry<Entity, Coordinate> entry : world.newCreaturesCoordinates.entrySet()) {

            world.coordinatesEntities.put(entry.getValue(), entry.getKey());
            world.creaturesCoordinates.put(entry.getKey(), entry.getValue());
        }
    }
}
