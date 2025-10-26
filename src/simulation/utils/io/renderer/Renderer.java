package simulation.utils.io.renderer;

import simulation.entity.Entity;
import simulation.world.Coordinate;
import simulation.world.MapOfWorld;
import simulation.utils.config.Config;

import java.util.Map;

public interface Renderer {
    String interval = " ";

    default void draw(Config config, MapOfWorld world) {
        StringBuilder stringBuilder = new StringBuilder();
        int count = 0;
        for (Map.Entry<Coordinate, Entity> entry : world.coordinatesEntities.entrySet()) {
            if (count < config.numberOfLines) {
                stringBuilder.append(getSprite(entry.getValue())).append(interval);
                count++;
            } else {
                System.out.println(stringBuilder);
                count = 1;
                stringBuilder = new StringBuilder();
                stringBuilder.append(getSprite(entry.getValue())).append(interval);
            }
        }
        System.out.println(stringBuilder + "\n");
    }

    String getSprite (Entity entity);
}
