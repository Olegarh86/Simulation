package simulation.actions;

import simulation.entity.creatures.Creature;
import simulation.entity.Entity;
import simulation.config.Config;
import simulation.world.Coordinate;
import simulation.world.MapOfWorld;

import java.util.HashMap;
import java.util.Map;

public class TurnActions implements Actions {

    @Override
    public void execute(MapOfWorld world, Config config) {
        Map<Creature, Coordinate> creaturesCoordinates = new HashMap<>();
        for (Map.Entry<Coordinate, Entity> entry : world.getCoordinatesEntities().entrySet()) {
            if (entry.getValue() instanceof Creature) {
                creaturesCoordinates.put((Creature) entry.getValue(), entry.getKey());
            }
        }
        allCreaturesMove(world, creaturesCoordinates);
    }

    private void allCreaturesMove(MapOfWorld world, Map<Creature, Coordinate> creaturesCoordinates) {
        for (Map.Entry<Creature, Coordinate> entry : creaturesCoordinates.entrySet()) {
            Coordinate startCoordinate = entry.getValue();
            Creature creature = entry.getKey();
            creature.makeMove(world, startCoordinate);
        }
    }
}
