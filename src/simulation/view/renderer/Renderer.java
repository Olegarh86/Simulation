package simulation.view.renderer;

import simulation.world.WorldMap;
import simulation.config.Config;

public interface Renderer {
    void draw(WorldMap world, Config config);
}
