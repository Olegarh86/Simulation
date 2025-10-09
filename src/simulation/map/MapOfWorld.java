package simulation.map;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import simulation.entity.*;
import simulation.utils.*;

public class MapOfWorld {
    public BiMap<Coordinate, Entity> biMap = HashBiMap.create();
    public BiMap<Entity, Coordinate> biMapOfCreatures = HashBiMap.create();
    public BiMap<Entity, Coordinate> newBiMapOfCreatures = HashBiMap.create();

    public MapOfWorld() {
        for (int str = 1; str <= Config.getWeight(); str++) {
            for (int col = 1; col <= Config.getHeight(); col++) {
                Coordinate tempCoordinate = new Coordinate(str, col);
                biMap.put(tempCoordinate, new EmptyCell());
            }
        }
    }
}
