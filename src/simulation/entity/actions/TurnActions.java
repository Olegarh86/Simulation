package simulation.entity.actions;

import simulation.entity.creatures.Creature;
import simulation.entity.Entity;
import simulation.utils.config.Config;
import simulation.world.Coordinate;
import simulation.world.MapOfWorld;

import java.util.Map;
import java.util.TreeMap;

public class TurnActions implements Actions {
    private final MapOfWorld world;
    private final Config config;


    public TurnActions(MapOfWorld world, Config config) {
        this.world = world;
        this.config = config;
    }

    @Override
    public void execute() {
        Map<Entity, Coordinate> creaturesCoordinates = new TreeMap<>();
        for (Map.Entry<Coordinate, Entity> entry : world.getCoordinatesEntities().entrySet()) {
            if (entry.getValue().isMovable()) {
                creaturesCoordinates.put(entry.getValue(), entry.getKey());
            }
        }
        allCreaturesMove(creaturesCoordinates);
    }

    private void allCreaturesMove(Map<Entity, Coordinate> creaturesCoordinates) {
        for (Map.Entry<Entity, Coordinate> entry : creaturesCoordinates.entrySet()) {
            Coordinate startCoordinate = entry.getValue();
            Creature creature = (Creature) entry.getKey();
            creature.makeMove(world, config, creature, startCoordinate);
        }
    }
}
