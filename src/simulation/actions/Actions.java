package simulation.actions;

import simulation.config.Config;
import simulation.world.MapOfWorld;

public interface Actions {
    void execute(MapOfWorld world, Config config);
}
