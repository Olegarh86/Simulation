package simulation.map;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import simulation.entity.*;
import simulation.entity.CreatorOfEmptyCells;
import simulation.utils.*;

import static simulation.map.Coordinate.getCoordinate;

public class MapOfWorld {
    public BiMap<Coordinate, Entity> biMap = HashBiMap.create();
    public BiMap<Entity, Coordinate> biMapOfCreatures = HashBiMap.create();
    public BiMap<Entity, Coordinate> newBiMapOfCreatures = HashBiMap.create();

    public MapOfWorld(Config config) {
        for (int line = 0; line < config.numberOfColumns; line++) {
            for (int column = 0; column < config.numberOfLines; column++) {
                Coordinate tempCoordinate = getCoordinate(line, column);
                biMap.put(tempCoordinate, new CreatorOfEmptyCells().createEntity(config));
            }
        }
    }
}
