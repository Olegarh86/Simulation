package simulation.utils;

import simulation.entity.Entity;
import simulation.map.Coordinate;
import simulation.map.MapOfWorld;

import java.util.Map;

public interface Renderer {

    default void draw(Config config, MapOfWorld map) {
        StringBuilder stringBuilder = new StringBuilder();
        int count = 0;
        for (Map.Entry<Coordinate, Entity> entry : map.biMap.entrySet()) {
            if (count < config.numberOfLines) {
                stringBuilder.append(getSprite(entry.getValue())).append(" ");
                count++;
            } else {
                System.out.println(stringBuilder);
                count = 1;
                stringBuilder = new StringBuilder();
                stringBuilder.append(getSprite(entry.getValue())).append(" ");
            }
        }
        System.out.println(stringBuilder + "\n");
    }

    String getSprite (Entity entity);
}
