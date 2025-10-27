package simulation.utils.io.renderer;

import simulation.entity.Entity;
import simulation.world.MapOfWorld;
import simulation.utils.config.Config;

public interface Renderer {
    void draw(Config config, MapOfWorld world);
    String getSprite (Entity entity);
}
