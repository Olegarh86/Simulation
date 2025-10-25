package simulation.map;
import simulation.entity.*;
import simulation.utils.config.Config;

import java.util.*;

import static simulation.map.Coordinate.getCoordinate;

public class MapOfWorld {

    public Map<Coordinate, Entity> coordinatesEntities = new TreeMap<>();
    public Map<Entity, Coordinate> creaturesCoordinates = new TreeMap<>();
    public Map<Entity, Coordinate> newCreaturesCoordinates = new TreeMap<>();

    public MapOfWorld(Config config) {
        for (int line = 0; line < config.numberOfColumns; line++) {
            for (int column = 0; column < config.numberOfLines; column++) {
                Coordinate tempCoordinate = getCoordinate(line, column);
                coordinatesEntities.put(tempCoordinate, new EmptyCell());
            }
        }
    }
}
