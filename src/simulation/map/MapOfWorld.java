package simulation.map;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import simulation.entity.*;
import simulation.utils.config.Config;

import java.util.*;

import static simulation.map.Coordinate.getCoordinate;

public class MapOfWorld {

    public BiMap<Coordinate, Entity> biMap = HashBiMap.create();
    public Map<Entity, Coordinate> biMapOfCreatures = new TreeMap<>();
    public Map<Entity, Coordinate> newBiMapOfCreatures = new TreeMap<>();

    public MapOfWorld(Config config) {
        for (int line = 0; line < config.numberOfColumns; line++) {
            for (int column = 0; column < config.numberOfLines; column++) {
                Coordinate tempCoordinate = getCoordinate(line, column);
                biMap.put(tempCoordinate, new EmptyCell());
            }
        }
    }
}
