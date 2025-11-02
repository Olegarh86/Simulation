package simulation.entity.actions;

import simulation.entity.creatures.Creature;
import simulation.entity.Entity;
import simulation.utils.config.Config;
import simulation.world.Coordinate;
import simulation.world.MapOfWorld;

import java.util.Map;
import java.util.TreeMap;

public class TurnActions implements Actions {

    @Override
    public void execute(MapOfWorld world, Config config) {
        Map<Creature, Coordinate> creaturesCoordinates = new TreeMap<>();
        for (Map.Entry<Coordinate, Entity> entry : world.getCoordinatesEntities().entrySet()) {
            if (entry.getValue().isMovable()) {
                creaturesCoordinates.put((Creature) entry.getValue(), entry.getKey());
            }
        }
        allCreaturesMove(world, config, creaturesCoordinates);
    }

    private void allCreaturesMove(MapOfWorld world, Config config, Map<Creature, Coordinate> creaturesCoordinates) {
        for (Map.Entry<Creature, Coordinate> entry : creaturesCoordinates.entrySet()) {
            Coordinate startCoordinate = entry.getValue();
            Creature creature = entry.getKey();
            creature.makeMove(world, config, creature, startCoordinate);
        }
    }
}
