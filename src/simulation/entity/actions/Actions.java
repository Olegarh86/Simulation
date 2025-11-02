package simulation.entity.actions;

import simulation.utils.config.Config;
import simulation.world.MapOfWorld;

public interface Actions {
    void execute(MapOfWorld world, Config config);
}
