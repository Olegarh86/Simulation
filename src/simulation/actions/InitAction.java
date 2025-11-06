package simulation.actions;

import simulation.entity.entityFactories.*;
import simulation.world.WorldMap;
import simulation.config.Config;

import java.util.List;

public class InitAction implements Action {

    @Override
    public void execute(WorldMap world, Config config) {
        List<Action> actions = getActions(config);
        for(Action action :  actions) {
            action.execute(world, config);
        }
    }

    private static List<Action> getActions(Config config) {
        SpawnAction rockSpawnAction = new SpawnAction(() -> new RockFactory().create(config),
                config.numberOfRocks);
        SpawnAction treeSpawnAction = new SpawnAction(() -> new TreeFactory().create(config),
                config.numberOfTrees);
        SpawnAction herbivoreSpawnAction = new SpawnAction(() -> new HerbivoreFactory().create(config),
                config.numberOfHerbivores);
        SpawnAction predatorSpawnAction = new SpawnAction(() -> new PredatorFactory().create(config),
                config.numberOfPredators);
        SpawnAction grassSpawnAction = new SpawnAction(() -> new GrassFactory().create(config),
                config.numberOfGrasses);
        return List.of(rockSpawnAction, treeSpawnAction, grassSpawnAction, herbivoreSpawnAction, predatorSpawnAction);
    }
}
