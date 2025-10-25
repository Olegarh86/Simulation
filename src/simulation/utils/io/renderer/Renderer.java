package simulation.utils.io.renderer;

import simulation.entity.Entity;
import simulation.map.Coordinate;
import simulation.map.MapOfWorld;
import simulation.utils.config.Config;
import simulation.utils.io.Output;

import java.util.Map;

public interface Renderer {
    String interval = " ";

    default void draw(Config config, MapOfWorld map, Output output) {
        StringBuilder stringBuilder = new StringBuilder();
        int count = 0;
        for (Map.Entry<Coordinate, Entity> entry : map.coordinatesEntities.entrySet()) {
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
