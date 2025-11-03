package simulation.actions;

import simulation.entity.entityFactories.*;
import simulation.world.MapOfWorld;
import simulation.config.Config;

import java.util.List;

public class InitActions implements Actions {

    @Override
    public void execute(MapOfWorld world, Config config) {
        List<Actions> actions = getActions(world, config);
        for(Actions action :  actions) {
            action.execute(world, config);
        }
    }

    private static List<Actions> getActions(MapOfWorld world, Config config) {
        SpawnAction rockSpawnAction = new SpawnAction(() -> new RockFactory().create(world, config),
                config.numberOfRocks);
        SpawnAction treeSpawnAction = new SpawnAction(() -> new TreeFactory().create(world, config),
                config.numberOfTrees);
        SpawnAction herbivoreSpawnAction = new SpawnAction(() -> new HerbivoreFactory().create(world, config),
                config.numberOfHerbivores);
        SpawnAction predatorSpawnAction = new SpawnAction(() -> new PredatorFactory().create(world, config),
                config.numberOfPredators);
        SpawnAction grassSpawnAction = new SpawnAction(() -> new GrassFactory().create(world, config),
                config.numberOfGrasses);
        return List.of(rockSpawnAction, treeSpawnAction, grassSpawnAction, herbivoreSpawnAction, predatorSpawnAction);
    }
}
