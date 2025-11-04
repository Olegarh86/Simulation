package simulation.view.renderer;

import simulation.world.MapOfWorld;
import simulation.config.Config;

public interface Renderer {
    void draw(MapOfWorld world, Config config);
    String getSprite (Object entity);
}
