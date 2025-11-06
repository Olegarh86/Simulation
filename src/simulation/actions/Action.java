package simulation.actions;

import simulation.config.Config;
import simulation.world.WorldMap;

public interface Action {
    void execute(WorldMap world, Config config);
}
