package simulation.view.renderer;

import simulation.world.MapOfWorld;
import simulation.config.Config;

public interface Renderer {
    void draw(Config config, MapOfWorld world);
    String getSprite (Object entity);
}
